package ca.ulaval.glo2003.interfaces.rest.factories;

import static ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorResponseFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CatchallErrorResponseFactoryTest {

  private static CatchallErrorResponseFactory catchallErrorResponseFactory;

  @BeforeAll
  public static void setUpFactory() {
    catchallErrorResponseFactory = new CatchallErrorResponseFactory();
  }

  @ParameterizedTest
  @MethodSource("provideResponseForException")
  public void create_withException_shouldCreateAssociatedResponse(
      Exception exception, String expectedResponse) {
    String response = catchallErrorResponseFactory.create(exception);

    assertEquals(expectedResponse, response);
  }

  private static Stream<Arguments> provideResponseForException() {
    return Stream.of(
        Arguments.of(new InvalidFormatException(), invalidFormat()),
        Arguments.of(mock(JsonProcessingException.class), couldNotParseJson()),
        Arguments.of(new Exception(), defaultResponse()));
  }
}
