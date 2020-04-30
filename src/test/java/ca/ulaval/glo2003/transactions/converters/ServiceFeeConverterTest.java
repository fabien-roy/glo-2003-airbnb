package ca.ulaval.glo2003.transactions.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.transactions.domain.ServiceFee;
import ca.ulaval.glo2003.transactions.exceptions.OutOfBoundsServiceFeeException;
import ca.ulaval.glo2003.transactions.rest.ServiceFeeRequest;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ServiceFeeConverterTest {

  private static ServiceFeeConverter serviceFeeConverter;

  private static ServiceFee serviceFee;

  private static ServiceFeeRequest serviceFeeRequest;

  @BeforeAll
  public static void setUpConverter() {
    serviceFeeConverter = new ServiceFeeConverter();
  }

  private void setUpRequest(BigDecimal serviceFeeValue) {
    serviceFeeRequest = new ServiceFeeRequest();
    serviceFeeRequest.setServiceFee(serviceFeeValue.doubleValue());
  }

  @ParameterizedTest
  @MethodSource("provideServiceFeeValues")
  public void fromRequest_shouldConvertServiceFee(BigDecimal serviceFeeValue) {
    setUpRequest(serviceFeeValue);

    serviceFee = serviceFeeConverter.fromRequest(serviceFeeRequest);

    assertEquals(serviceFeeValue, serviceFee.toBigDecimal());
  }

  private static Stream<Arguments> provideServiceFeeValues() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(0.0)),
        Arguments.of(BigDecimal.valueOf(15.0)),
        Arguments.of(BigDecimal.valueOf(1.0)),
        Arguments.of(BigDecimal.valueOf(12.123), Arguments.of(BigDecimal.valueOf(0.01))));
  }

  @ParameterizedTest
  @MethodSource("provideLowerThanMinimumServiceFeeValues")
  public void fromRequest_withLowerThanMinimumServiceFee_shouldThrowOutOfBoundsServiceFeeException(
      BigDecimal serviceFeeValue) {
    setUpRequest(serviceFeeValue);

    assertThrows(
        OutOfBoundsServiceFeeException.class,
        () -> serviceFeeConverter.fromRequest(serviceFeeRequest));
  }

  private static Stream<Arguments> provideLowerThanMinimumServiceFeeValues() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(-1)),
        Arguments.of(BigDecimal.valueOf(-12), Arguments.of(BigDecimal.valueOf(-0.01))));
  }

  @ParameterizedTest
  @MethodSource("provideHigherThanMinimumServiceFeeValues")
  public void fromRequest_withHigherThanMinimumServiceFee_shouldThrowOutOfBoundsServiceFeeException(
      BigDecimal serviceFeeValue) {
    setUpRequest(serviceFeeValue);

    assertThrows(
        OutOfBoundsServiceFeeException.class,
        () -> serviceFeeConverter.fromRequest(serviceFeeRequest));
  }

  private static Stream<Arguments> provideHigherThanMinimumServiceFeeValues() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(100.13)),
        Arguments.of(BigDecimal.valueOf(55.55), Arguments.of(BigDecimal.valueOf(15.01))));
  }
}
