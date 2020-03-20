package ca.ulaval.glo2003.interfaces.rest.factories;

import static ca.ulaval.glo2003.interfaces.rest.factories.ExternalServiceErrorResponseFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.interfaces.exceptions.ExternalServiceException;
import ca.ulaval.glo2003.interfaces.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.exceptions.UnreachableZippopotamusServerException;
import java.util.stream.Stream;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ExternalServiceErrorStatusFactoryTest {

  private static ExternalServiceErrorStatusFactory externalServiceErrorStatusFactory;

  @BeforeAll
  public static void setUpFactory() {
    externalServiceErrorStatusFactory = new ExternalServiceErrorStatusFactory();
  }

  @ParameterizedTest
  @MethodSource("provideStatusForExternalServiceException")
  public void create_withExternalServiceException_shouldCreateAssociatedStatus(
      ExternalServiceException exception, int expectedStatus) {
    int status = externalServiceErrorStatusFactory.create(exception);

    assertEquals(expectedStatus, status);
  }

  @Test
  public void create_withException_shouldCreateDefaultStatus() {
    Exception exception = new Exception();
    int expectedStatus = new CatchallErrorStatusFactory().create(exception);

    int status = externalServiceErrorStatusFactory.create(exception);

    assertEquals(expectedStatus, status);
  }

  private static Stream<Arguments> provideStatusForExternalServiceException() {
    return Stream.of(
        Arguments.of(new InvalidZipCodeException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new NonExistingZipCodeException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(
            new UnreachableZippopotamusServerException(), HttpStatus.SERVICE_UNAVAILABLE_503));
  }
}
