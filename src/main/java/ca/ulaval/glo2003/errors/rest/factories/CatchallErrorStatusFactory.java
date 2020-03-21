package ca.ulaval.glo2003.errors.rest.factories;

import ca.ulaval.glo2003.errors.exceptions.InvalidFormatException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.jetty.http.HttpStatus;

public class CatchallErrorStatusFactory {

  public int create(Exception exception) {
    if (exception instanceof InvalidFormatException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof JsonProcessingException) {
      return HttpStatus.BAD_REQUEST_400;
    } else {
      return defaultStatus();
    }
  }

  protected static int defaultStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
