package ca.ulaval.glo2003.reports.exceptions;

public abstract class ReportException extends RuntimeException {

  public ReportException(String message) {
    super(message);
  }
}
