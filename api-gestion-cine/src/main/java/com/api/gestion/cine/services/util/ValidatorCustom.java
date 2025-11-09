package com.api.gestion.cine.services.util;

import java.time.LocalDate;

public class ValidatorCustom {

  private final static String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

  /*
   * Verifica si las fechas son válidas solo si están presentes.
   * Permite que ambas sean opcionales.
   */
  public static boolean isValidDate(String startDate, String endDate) {
    if (isNullOrEmpty(startDate) && isNullOrEmpty(endDate)) {
      return true;
    }

    if (!isNullOrEmpty(startDate) && !startDate.matches(DATE_PATTERN)) {
      return false;
    }

    if (!isNullOrEmpty(endDate) && !endDate.matches(DATE_PATTERN)) {
      return false;
    }

    return true;
  }

  /*
   * Convierte cadenas a LocalDate si existen.
   * Retorna arreglo [startDate, endDate].
   */
  public static LocalDate[] convertDateStringToLocalDate(String startDate, String endDate) {
    LocalDate[] dates = new LocalDate[2];

    if (!isNullOrEmpty(startDate)) {
      dates[0] = FormatterDateCustom.parseStringToDate(startDate);
    }

    if (!isNullOrEmpty(endDate)) {
      dates[1] = FormatterDateCustom.parseStringToDate(endDate);
    }

    return dates;
  }

  public static boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty() || "null".equalsIgnoreCase(value.trim());
  }

  public static LocalDate parseStringToDate(String dateStr) {
    return FormatterDateCustom.parseStringToDate(dateStr);
  }

}
