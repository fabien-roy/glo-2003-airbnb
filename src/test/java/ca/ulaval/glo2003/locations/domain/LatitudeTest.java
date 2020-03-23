package ca.ulaval.glo2003.locations.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LatitudeTest {

  @ParameterizedTest
  @MethodSource("provideDataForToKm")
  public void toKm_shouldGetValueInKm(double value, double valueInKm) {
    Latitude latitude = new Latitude(value);

    assertEquals(valueInKm, latitude.toKm());
  }

  private static Stream<Arguments> provideDataForToKm() {
    return Stream.of(Arguments.of(1, 110.574), Arguments.of(2, 221.148), Arguments.of(3, 331.722));
  }
}
