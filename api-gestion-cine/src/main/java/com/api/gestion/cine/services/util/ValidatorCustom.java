package com.api.gestion.cine.services.util;

import java.time.LocalDate;

public class ValidatorCustom {

  private final static String DATE_PATTERN = "^\\d{4}/\\d{2}/\\d{2}$";

  public static boolean isValidDate(String startDate, String endDate) {

    return startDate.matches(DATE_PATTERN) && endDate.matches(DATE_PATTERN);
  }

  public static LocalDate[] convertDateStringToLocalDate(String startDate, String endDate) {

    LocalDate[] dates = new LocalDate[2];
    if (startDate != null && !startDate.trim().isEmpty()) {
      dates[0] = FormatterDateCustom.parseStringToDate(startDate);
    }
    if (endDate != null && !endDate.trim().isEmpty()) {
      dates[1] = FormatterDateCustom.parseStringToDate(endDate);
    }
    return dates;
  }

  public static boolean isNullOrEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }
}
