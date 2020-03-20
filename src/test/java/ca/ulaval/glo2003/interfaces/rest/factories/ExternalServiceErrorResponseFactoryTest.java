package ca.ulaval.glo2003.interfaces.rest.factories;

import static ca.ulaval.glo2003.interfaces.rest.factories.ExternalServiceErrorResponseFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.interfaces.exceptions.ExternalServiceException;
import ca.ulaval.glo2003.interfaces.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.exceptions.UnreachableZippopotamusServerException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ExternalServiceErrorResponseFactoryTest {

  private static ExternalServiceErrorResponseFactory externalServiceErrorResponseFactory;

  @BeforeAll
  public static void setUpFactory() {
    externalServiceErrorResponseFactory = new ExternalServiceErrorResponseFactory();
  }

  @ParameterizedTest
  @MethodSource("provideResponseForExternalServiceException")
  public void create_withExternalServiceException_shouldCreateAssociatedResponse(
      ExternalServiceException exception, String expectedResponse) {
    String response = externalServiceErrorResponseFactory.create(exception);

    assertEquals(expectedResponse, response);
  }

  @Test
  public void create_withException_shouldCreateDefaultResponse() {
    Exception exception = new Exception();
    String expectedResponse = new CatchallErrorResponseFactory().create(exception);

    String response = externalServiceErrorResponseFactory.create(exception);

    assertEquals(expectedResponse, response);
  }

  private static Stream<Arguments> provideResponseForExternalServiceException() {
    return Stream.of(
        Arguments.of(new InvalidZipCodeException(), invalidZipCode()),
        Arguments.of(new NonExistingZipCodeException(), nonExistingZipCode()),
        Arguments.of(
            new UnreachableZippopotamusServerException(), unreachableZippopotamusServer()));
  }
}
