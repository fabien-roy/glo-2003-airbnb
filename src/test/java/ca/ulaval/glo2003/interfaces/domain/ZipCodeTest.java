package ca.ulaval.glo2003.interfaces.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ZipCodeTest {

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotZipCode() {
    String zipValue = "12345";
    ZipCode zipCode = new ZipCode(zipValue);

    boolean result = zipCode.equals(zipValue);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    String value = "000000";
    String otherValue = "12345";
    ZipCode zipCode = new ZipCode(value);
    ZipCode otherZipCode = new ZipCode(otherValue);

    boolean result = zipCode.equals(otherZipCode);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenValuesAreEqual() {
    String value = "12345";
    ZipCode zipCode = new ZipCode(value);
    ZipCode otherZipCode = new ZipCode(value);

    boolean result = zipCode.equals(otherZipCode);

    assertTrue(result);
  }

  @ParameterizedTest
  @MethodSource("provideDataForCalculateDistanceBetweenCoordinates")
  public void calculateDistanceBetweenCoordinates_shouldReturnCorrectDistance(
      String latitude1,
      String longitude1,
      String latitude2,
      String longitude2,
      double expectedDistance) {
    double distance =
        ZipCode.calculateDistanceBetweenCoordinates(latitude1, longitude1, latitude2, longitude2);
    assertEquals(expectedDistance, distance);
  }

  private static Stream<Arguments> provideDataForCalculateDistanceBetweenCoordinates() {
    return Stream.of(
        Arguments.of(
            "40.759341",
            "-73.995611",
            "46.753077",
            "-73.977154",
            892.0431709382362), // YOTEL NY City to Grand Central Terminal
        Arguments.of(
            "43.039643",
            "-87.905641",
            "42.334705",
            "-83.055317",
            326.934371636417), // The Pfister Hotel, Milhaukee to Beacon Park, Detroit
        Arguments.of(
            "34.101990",
            "-118.341873",
            "33.946834",
            "-118.408961",
            31.281231789505952), // Hotel Roosevelt LA to LA International Airport
        Arguments.of(
            "46.783506",
            "-71.275291",
            "46.783506",
            "-71.275291",
            0.0) // Laval University to Laval University (beware, not in the united states)
        );
  }

  @ParameterizedTest
  @MethodSource("provideDataForisWithinRadius_withMaxDistance")
  public void isWithinRadius_withMaxDistance_shouldReturnCorrectAnswer(
      ZipCode zipCode, ZipCode origin, double maxDistance, double expectedAnswer) {
    Boolean answer = zipCode.isWithinRadius(origin, maxDistance);
    assertEquals(expectedAnswer, answer);
  }

  private static Stream<Arguments> provideDataForisWithinRadius_withMaxDistance() {
    ZipCode yotelNYCity = new ZipCode("10036", "40.759341", "-73.995611");
    ZipCode grandCentralTerminal = new ZipCode("10017", "46.753077", "-73.977154");
    ZipCode pfisterHotelMilhaukee = new ZipCode("53202", "43.039643", "-87.905641");
    ZipCode beaconParkDetroit = new ZipCode("48226", "42.334705", "-83.055317");
    ZipCode hotelRooseveltLA = new ZipCode("90028", "34.101990", "-118.341873");
    ZipCode laInternationalAirport = new ZipCode("90045", "33.946834", "-118.408961");

    return Stream.of(
        Arguments.of(
            yotelNYCity,
            grandCentralTerminal,
            900,
            true), // YOTEL NY City to Grand Central Terminal
        Arguments.of(
            pfisterHotelMilhaukee,
            beaconParkDetroit,
            326.934371636417,
            true), // The Pfister Hotel, Milhaukee to Beacon Park, Detroit
        Arguments.of(
            hotelRooseveltLA,
            laInternationalAirport,
            20,
            false), // Hotel Roosevelt LA to LA International Airport
        Arguments.of(
            laInternationalAirport,
            laInternationalAirport,
            0.0,
            true) // LA International Airport to LA International Airport
        );
  }

  @ParameterizedTest
  @MethodSource("provideDataForisWithinRadius_withoutMaxDistance")
  public void isWithinRadius_withoutMaxDistance_shouldReturnCorrectAnswer(
      ZipCode zipCode, ZipCode origin, double expectedAnswer) {
    Boolean answer = zipCode.isWithinRadius(origin);
    assertEquals(expectedAnswer, answer);
  }

  private static Stream<Arguments> provideDataForisWithinRadius_withoutMaxDistance() {
    ZipCode yotelNYCity = new ZipCode("10036", "40.759341", "-73.995611");
    ZipCode grandCentralTerminal = new ZipCode("10017", "46.753077", "-73.977154");
    ZipCode laInternationalAirport = new ZipCode("90045", "33.946834", "-118.408961");
    ZipCode croationChurchNY = new ZipCode("10036", "40.759203", "-73.9970572");

    return Stream.of(
        Arguments.of(
            yotelNYCity, grandCentralTerminal, false), // YOTEL NY City to Grand Central Terminal
        Arguments.of(
            laInternationalAirport,
            laInternationalAirport,
            true), // LA International Airport to LA International Airport
        Arguments.of(
            yotelNYCity, croationChurchNY, true) //  YOTEL NY City to Croation Church New York
        );
  }
}
