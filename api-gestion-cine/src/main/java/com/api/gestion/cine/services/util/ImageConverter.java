package com.api.gestion.cine.services.util;

import java.io.*;
import java.net.URLConnection;
import javax.imageio.ImageIO;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import com.api.gestion.cine.exceptions.ImageFormatException;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

public class ImageConverter {
  private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

  public static byte[] convertImage(byte[] imageBytes) throws ImageFormatException, IOException {
    if (imageBytes != null && imageBytes.length > 0) {

      if (imageBytes.length > MAX_SIZE) {
        throw new ImageFormatException("El tamaño de la imagen supera los 5MB.");
      }

      // Detectar el tipo MIME
      String detectedType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imageBytes));
      if (detectedType == null || !detectedType.startsWith("image/")) {
        throw new ImageFormatException("El archivo no es una imagen válida.");
      }

      BufferedImage img;
      try (ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes)) {
        img = ImageIO.read(bais);
        if (img == null) {
          throw new ImageFormatException("El archivo no es una imagen válida.");
        }
      }

      // Si ya es JPEG, devolver original
      if (detectedType.equals("image/jpeg")) {
        return imageBytes;
      } else {
        // Convertir a JPEG
        return convertToJpeg(img);
      }
    }
    return null;
  }

  private static byte[] convertToJpeg(BufferedImage image) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    // Convertir la imagen a RGB
    BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

    // Pintar sobre fondo blanco
    Graphics2D g = newImage.createGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());
    g.drawImage(image, 0, 0, null);
    g.dispose();

    // Escribir como JPEG
    ImageIO.write(newImage, "jpg", baos);
    return baos.toByteArray();
  }

  public byte[] processImage(FormDataBodyPart fotoPart) throws Exception {
    if (fotoPart != null) {
      InputStream fileInputStream = fotoPart.getValueAs(InputStream.class);
      byte[] imageBytes = convertInputStreamToByteArray(fileInputStream);
      return ImageConverter.convertImage(imageBytes);
    }
    return null;
  }

  private byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int nRead;
    byte[] data = new byte[1024];
    while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, nRead);
    }
    return buffer.toByteArray();
  }
}