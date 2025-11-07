package com.api.gestion.cine.services.user;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import com.api.gestion.cine.db.user.CreateUserDB;
import com.api.gestion.cine.db.user.UserDB;
import com.api.gestion.cine.dto.users.CreateUserDTO;
import com.api.gestion.cine.exceptions.UserAlreadyExists;
import com.api.gestion.cine.services.util.ImageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateUserService {

  private final UserDB userDB = new UserDB();
  private final CreateUserDB createUserDB = new CreateUserDB();
  private final ImageConverter imageConverter = new ImageConverter();

  public void createUser(CreateUserDTO userDTO) throws Exception {

    if (isUserExisting(userDTO)) {
      throw new UserAlreadyExists("El usuario, correo o teléfono ya existen");
    }

    int idRol = userDB.getIdRol(userDTO.getTipoUsuario());

    createUserDB.createUser(userDTO, idRol);
  }

  public boolean isUserExisting(CreateUserDTO userDTO) throws Exception {
    return userDB.usuarioExiste(userDTO.getUsuario())
        || userDB.correoExiste(userDTO.getCorreo())
        || userDB.telefonoExiste(userDTO.getTelefono());
  }

  public CreateUserDTO convertFormDataToDTO(FormDataBodyPart userDataPart, FormDataBodyPart fotoPart) throws Exception {
    if (userDataPart == null) {
      throw new IllegalArgumentException("Los datos del usuario son obligatorios");
    }

    String jsonData = userDataPart.getValue();
    ObjectMapper mapper = new ObjectMapper();
    CreateUserDTO userDTO = mapper.readValue(jsonData, CreateUserDTO.class);

    if (fotoPart != null) {
      byte[] processedImage = imageConverter.processImage(fotoPart);
      userDTO.setFoto(processedImage);
    }

    return userDTO;
  }
}
