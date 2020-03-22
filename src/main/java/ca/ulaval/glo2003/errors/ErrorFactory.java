package ca.ulaval.glo2003.errors;

import org.eclipse.jetty.http.HttpStatus;

public interface ErrorFactory {

  Exception canHandle();

  String createResponse();

  HttpStatus createStatus();
}
