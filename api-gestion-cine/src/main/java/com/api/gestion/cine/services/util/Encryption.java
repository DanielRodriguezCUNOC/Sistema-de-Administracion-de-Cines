package com.api.gestion.cine.services.util;

import java.util.Base64;

public class Encryption {

  public static String encryptPassword(String password, String email) {
    String saltedPassword = password + email;
    return Base64.getEncoder().encodeToString(saltedPassword.getBytes());
  }

  public static String decryptPassword(String encryptedPassword) {
    byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
    return new String(decodedBytes);
  }
}
