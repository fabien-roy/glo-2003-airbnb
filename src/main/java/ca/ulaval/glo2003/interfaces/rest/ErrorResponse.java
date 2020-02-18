package ca.ulaval.glo2003.interfaces.rest;

public class ErrorResponse {

  private String error;
  private String description;

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
