package com.api.gestion.cine.services.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NameFileGenerator {

  public String generateFileName(String baseName, String extension) {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
    String timestamp = now.format(formatter);
    return baseName + "_" + timestamp + "." + extension;
  }

}
