package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidPublicKeyException extends RuntimeException {

  public InvalidPublicKeyException() {
    super("INVALID_PUBLIC_KEY");
  }
}