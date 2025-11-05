package com.api.gestion.cine.exceptions;

public class ReportServiceException extends Exception {
  public ReportServiceException(String message) {
    super(message);
  }

  public ReportServiceException(String message, Throwable cause) {
    super(message, cause);
  }

}
