package ca.ulaval.glo2003.errors.rest.factories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.errors.exceptions.InvalidFormatException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.stream.Stream;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CatchallErrorStatusFactoryTest {

  private static CatchallErrorStatusFactory catchallErrorStatusFactory;

  @BeforeAll
  public static void setUpFactory() {
    catchallErrorStatusFactory = new CatchallErrorStatusFactory();
  }

  @ParameterizedTest
  @MethodSource("provideStatusForException")
  public void create_withException_shouldCreateAssociatedStatus(
      Exception exception, int expectedStatus) {
    int status = catchallErrorStatusFactory.create(exception);

    assertEquals(expectedStatus, status);
  }

  private static Stream<Arguments> provideStatusForException() {
    return Stream.of(
        Arguments.of(new InvalidFormatException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(mock(JsonProcessingException.class), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new Exception(), HttpStatus.BAD_REQUEST_400));
  }
}
