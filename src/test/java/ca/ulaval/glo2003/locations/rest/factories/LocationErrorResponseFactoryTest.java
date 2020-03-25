package ca.ulaval.glo2003.locations.rest.factories;

import static ca.ulaval.glo2003.locations.rest.factories.LocationErrorResponseFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.errors.rest.factories.CatchallErrorResponseFactory;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.LocationException;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LocationErrorResponseFactoryTest {

  private static LocationErrorResponseFactory locationErrorResponseFactory;

  @BeforeAll
  public static void setUpFactory() {
    locationErrorResponseFactory = new LocationErrorResponseFactory();
  }

  @ParameterizedTest
  @MethodSource("provideResponseForExternalServiceException")
  public void create_withExternalServiceException_shouldCreateAssociatedResponse(
      LocationException exception, String expectedResponse) {
    String response = locationErrorResponseFactory.create(exception);

    assertEquals(expectedResponse, response);
  }

  @Test
  public void create_withException_shouldCreateDefaultResponse() {
    Exception exception = new Exception();
    String expectedResponse = new CatchallErrorResponseFactory().create(exception);

    String response = locationErrorResponseFactory.create(exception);

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
