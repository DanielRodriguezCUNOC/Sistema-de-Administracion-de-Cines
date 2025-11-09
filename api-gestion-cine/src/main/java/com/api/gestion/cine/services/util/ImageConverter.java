package com.api.gestion.cine.services.util;

import java.io.*;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import com.api.gestion.cine.exceptions.ImageFormatException;

public class ImageConverter {
  private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

  public byte[] convertImage(byte[] imageBytes) throws ImageFormatException, IOException {
    if (imageBytes == null || imageBytes.length == 0) {
      return null;
    }

    if (imageBytes.length > MAX_SIZE) {
      throw new ImageFormatException("El tamaño de la imagen supera los 5MB.");
    }

    // * Detectar el tipo MIME
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

    if ("image/jpeg".equals(detectedType)) {
      return imageBytes;
    } else {
      return convertToJpeg(img);
    }
  }

  private byte[] convertToJpeg(BufferedImage image) throws IOException {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

      Graphics2D g = newImage.createGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());
      g.drawImage(image, 0, 0, null);
      g.dispose();

      ImageIO.write(newImage, "jpg", baos);
      return baos.toByteArray();
    }
  }

  public byte[] processImage(FormDataBodyPart fotoPart) throws Exception {
    if (fotoPart == null) {
      return null;
    }

    try (InputStream fileInputStream = fotoPart.getEntityAs(InputStream.class)) {
      byte[] imageBytes = convertInputStreamToByteArray(fileInputStream);
      return this.convertImage(imageBytes);
    }
  }

  private byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
    try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
      byte[] data = new byte[4096];
      int bytesRead;
      while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, bytesRead);
      }
      return buffer.toByteArray();
    }
  }
}
