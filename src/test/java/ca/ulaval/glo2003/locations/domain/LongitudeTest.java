package ca.ulaval.glo2003.locations.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongitudeTest {

  @ParameterizedTest
  @MethodSource("provideDataForToKm")
  public void toKm_shouldGetValueInKm(double value, double valueInKm) {
    Longitude longitude = new Longitude(value);

    assertEquals(valueInKm, longitude.toKm());
  }

  private static Stream<Arguments> provideDataForToKm() {
    return Stream.of(Arguments.of(1, 111.32), Arguments.of(2, 222.64), Arguments.of(3, 333.96));
  }
}
