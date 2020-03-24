package ca.ulaval.glo2003.locations.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LatitudeTest {

  private static double value = 1;
  private static Latitude latitude = new Latitude(value);

  @ParameterizedTest
  @MethodSource("provideDataForToKm")
  public void toKm_shouldGetValueInKm(double value, double valueInKm) {
    Latitude latitude = new Latitude(value);

    assertEquals(valueInKm, latitude.toKm());
  }

  private static Stream<Arguments> provideDataForToKm() {
    return Stream.of(Arguments.of(1, 110.574), Arguments.of(2, 221.148), Arguments.of(3, 331.722));
  }

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotLatitude() {
    String other = "12345";

    boolean result = latitude.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    Latitude other = new Latitude(2);

    boolean result = latitude.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenValuesAreEqual() {
    Latitude other = new Latitude(value);

    boolean result = latitude.equals(other);

    assertTrue(result);
  }
}
