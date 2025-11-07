package com.api.gestion.cine.services.util;

import java.time.LocalDate;

public class FormatterDateCustom {

  public static String formatDateToString(LocalDate date) {
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return date.format(formatter);
  }

  public static LocalDate parseStringToDate(String dateString) {
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(dateString, formatter);
  }
}
