package com.api.gestion.cine.services.user;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import com.api.gestion.cine.db.user.CreateUserDB;
import com.api.gestion.cine.db.user.UserDB;
import com.api.gestion.cine.dto.users.CreateUserDTO;
import com.api.gestion.cine.exceptions.ImageFormatException;
import com.api.gestion.cine.exceptions.UserAlreadyExists;
import com.api.gestion.cine.services.util.Encryption;
import com.api.gestion.cine.services.util.ImageConverter;

public class CreateUserService {

  private final UserDB userDB = new UserDB();
  private final CreateUserDB createUserDB = new CreateUserDB();
  private final ImageConverter imageConverter = new ImageConverter();

  public void createUser(CreateUserDTO userDTO) throws Exception {

    if (isUserExisting(userDTO)) {
      throw new UserAlreadyExists("El usuario, correo o teléfono ya existen");
    }

    int idRol = userDB.getIdRol(userDTO.getTipoUsuario());

    String encryptedPassword = Encryption.encryptPassword(userDTO.getPassword(), userDTO.getCorreo());
    userDTO.setPassword(encryptedPassword);

    createUserDB.createUser(userDTO, idRol);
  }

  public boolean isUserExisting(CreateUserDTO userDTO) throws Exception {
    return userDB.usuarioExiste(userDTO.getUsuario())
        || userDB.correoExiste(userDTO.getCorreo())
        || userDB.telefonoExiste(userDTO.getTelefono());
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

}
