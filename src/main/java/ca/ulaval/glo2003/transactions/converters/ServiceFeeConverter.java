package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.admin.domain.ServiceFee;
import ca.ulaval.glo2003.admin.rest.ServiceFeeRequest;
import ca.ulaval.glo2003.transactions.exceptions.OutOfBoundsServiceFeeException;
import java.math.BigDecimal;

public class ServiceFeeConverter {

  private static final int MINIMUM = 0;
  private static final int MAXIMUM = 15;

  public ServiceFee fromRequest(ServiceFeeRequest request) {
    validateServiceFee(request.getServiceFee());
    return new ServiceFee(BigDecimal.valueOf(request.getServiceFee()));
  }

  private void validateServiceFee(double serviceFee) {
    if (serviceFee <= MINIMUM || serviceFee >= MAXIMUM) {
      throw new OutOfBoundsServiceFeeException();
    }
  }
}
