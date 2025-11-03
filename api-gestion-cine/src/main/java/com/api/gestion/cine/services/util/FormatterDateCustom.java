package com.api.gestion.cine.services.util;

public class FormatterDateCustom {

  public static String formatDateToString(java.time.LocalDate date) {
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return date.format(formatter);
  }

  public static java.time.LocalDate parseStringToDate(String dateString) {
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return java.time.LocalDate.parse(dateString, formatter);
  }
}
