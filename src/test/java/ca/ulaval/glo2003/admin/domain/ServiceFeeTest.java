package ca.ulaval.glo2003.admin.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ServiceFeeTest {

  @Test
  public void isSet_withValue_shouldReturnTrue() {
    ServiceFee serviceFee = new ServiceFee(BigDecimal.ONE);

    boolean result = serviceFee.isSet();

    assertTrue(result);
  }

  @Test
  public void isSet_withoutValue_shouldReturnFalse() {
    ServiceFee serviceFee = new ServiceFee(null);

    boolean result = serviceFee.isSet();

    assertFalse(result);
  }
}
