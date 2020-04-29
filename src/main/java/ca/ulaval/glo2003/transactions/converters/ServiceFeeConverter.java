package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.transactions.domain.ServiceFee;
import ca.ulaval.glo2003.transactions.exceptions.OutOfBoundsServiceFeeException;
import ca.ulaval.glo2003.transactions.rest.ServiceFeeRequest;
import java.math.BigDecimal;

// TODO : Test ServiceFeeConverter
public class ServiceFeeConverter {

  private static final int MINIMUM = 0;
  private static final int MAXIMUM = 15;

  public ServiceFee fromRequest(ServiceFeeRequest request) {
    validateServiceFee(request.getServiceFee());
    return new ServiceFee(BigDecimal.valueOf(request.getServiceFee()));
  }

  private void validateServiceFee(double serviceFee) {
    if (serviceFee <= MINIMUM && serviceFee >= MAXIMUM) {
      throw new OutOfBoundsServiceFeeException();
    }
  }
}
