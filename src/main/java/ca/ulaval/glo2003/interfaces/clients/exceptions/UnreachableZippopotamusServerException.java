package ca.ulaval.glo2003.interfaces.clients.exceptions;

public class UnreachableZippopotamusServerException extends RuntimeException {

  public UnreachableZippopotamusServerException() {
    super("ZPPOPOTAMUS_UNREACHABLE");
  }
}
