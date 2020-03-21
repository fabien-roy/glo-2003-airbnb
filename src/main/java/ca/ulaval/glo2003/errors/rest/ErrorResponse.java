package ca.ulaval.glo2003.errors.rest;

public class ErrorResponse {

  private java.lang.String error;
  private java.lang.String description;

  public ErrorResponse(java.lang.String error, java.lang.String description) {
    this.error = error;
    this.description = description;
  }

  public java.lang.String getError() {
    return error;
  }

  public java.lang.String getDescription() {
    return description;
  }
}
