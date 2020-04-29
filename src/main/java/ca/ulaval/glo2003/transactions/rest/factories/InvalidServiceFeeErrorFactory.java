package ca.ulaval.glo2003.transactions.rest.factories;

import ca.ulaval.glo2003.transactions.exceptions.InvalidServiceFeeException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidServiceFeeErrorFactory extends TransactionErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidServiceFeeException.class;
  }

  protected String getError() {
    return "INVALID_SERVICE_FEE";
  }

  protected String getDescription() {
    return "service fee should be a number greater than or equal to 0";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
