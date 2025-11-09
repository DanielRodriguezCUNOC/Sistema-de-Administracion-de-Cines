package com.api.gestion.cine.services.sysadmin;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import com.api.gestion.cine.db.user.CreateUserDB;
import com.api.gestion.cine.db.user.UserDB;
import com.api.gestion.cine.dto.sysadmin.CreateCinemaAdminDTO;
import com.api.gestion.cine.dto.users.CreateUserDTO;
import com.api.gestion.cine.exceptions.ImageFormatException;
import com.api.gestion.cine.services.util.ImageConverter;

public class SystemAdminService {

  private ImageConverter imageConverter = new ImageConverter();
  private UserDB userDB = new UserDB();
  private CreateUserDB createUserDB = new CreateUserDB();

  public void createAdmin(CreateCinemaAdminDTO adminDTO) throws Exception {

    if (isUserExisting(adminDTO)) {
      throw new Exception("El usuario, correo o teléfono ya existen");

    }

    String encryptedPassword = com.api.gestion.cine.services.util.Encryption.encryptPassword(adminDTO.getPassword(),
        adminDTO.getCorreo());
    adminDTO.setPassword(encryptedPassword);

    createUserDB.createAdminCinema(adminDTO, adminDTO.getIdRol());

  }

  public CreateUserDTO convertFormDataToDTO(String nombreCompleto, String tipoUsuario, String usuario, String password,
      String correo, String telefono, FormDataBodyPart fotoPart) throws Exception {

    CreateUserDTO userDTO = new CreateUserDTO();
    userDTO.setNombreCompleto(nombreCompleto);
    userDTO.setTipoUsuario(tipoUsuario);
    userDTO.setUsuario(usuario);
    userDTO.setPassword(password);
    userDTO.setCorreo(correo);
    userDTO.setTelefono(telefono);

    if (fotoPart != null) {
      try {
        byte[] processedImage = imageConverter.processImage(fotoPart);
        userDTO.setFoto(processedImage);
      } catch (ImageFormatException e) {
        throw new IllegalArgumentException("Formato de imagen no válido. Se aceptan JPG, PNG y GIF.");
      } catch (Exception e) {
        throw new Exception("Error al procesar la imagen de perfil.");
      }
    }
    return userDTO;
  }

  public boolean isUserExisting(CreateCinemaAdminDTO adminDTO) throws Exception {
    return userDB.usuarioExiste(adminDTO.getUsuario())
        || userDB.correoExiste(adminDTO.getCorreo())
        || userDB.telefonoExiste(adminDTO.getTelefono());
  }

}
