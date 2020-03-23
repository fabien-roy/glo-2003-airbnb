package ca.ulaval.glo2003.locations.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongitudeTest {

  private static double value = 1;
  private static Longitude longitude = new Longitude(value);

  @ParameterizedTest
  @MethodSource("provideDataForToKm")
  public void toKm_shouldGetValueInKm(double value, double valueInKm) {
    Longitude longitude = new Longitude(value);

    assertEquals(valueInKm, longitude.toKm());
  }

  private static Stream<Arguments> provideDataForToKm() {
    return Stream.of(Arguments.of(1, 111.32), Arguments.of(2, 222.64), Arguments.of(3, 333.96));
  }

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotLongitude() {
    String other = "12345";

    boolean result = longitude.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    Longitude other = new Longitude(2);

    boolean result = longitude.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenValuesAreEqual() {
    Longitude other = new Longitude(value);

    boolean result = longitude.equals(other);

    assertTrue(result);
  }
}
