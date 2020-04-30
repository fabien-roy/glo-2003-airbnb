package ca.ulaval.glo2003.transactions.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.transactions.exceptions.InvalidServiceFeeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ServiceFeeDeserializerTest {

  private static ServiceFeeDeserializer serviceFeeDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    serviceFeeDeserializer = new ServiceFeeDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidServiceFeeException() {
    assertThrows(InvalidServiceFeeException.class, () -> serviceFeeDeserializer.throwException());
  }
}
