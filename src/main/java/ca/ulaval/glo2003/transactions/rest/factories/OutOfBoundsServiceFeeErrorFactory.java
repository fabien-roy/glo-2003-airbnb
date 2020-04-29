package ca.ulaval.glo2003.transactions.rest.factories;

import ca.ulaval.glo2003.transactions.exceptions.OutOfBoundsServiceFeeException;
import org.eclipse.jetty.http.HttpStatus;

public class OutOfBoundsServiceFeeErrorFactory extends TransactionErrorFactory {

  protected Class<?> getAssociatedException() {
    return OutOfBoundsServiceFeeException.class;
  }

  protected String getError() {
    return "OUT_OF_BOUNDS_SERVICE_FEE";
  }

  protected String getDescription() {
    return "service fee should be a number between 0 and 15 inclusively";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
