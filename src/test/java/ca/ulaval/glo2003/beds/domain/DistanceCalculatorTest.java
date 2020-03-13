package ca.ulaval.glo2003.beds.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DistanceCalculatorTest {

  @ParameterizedTest
  @MethodSource("provideDataForCalculateDistanceBetweenCoordinates")
  public void calculateDistanceBetweenCoordinates_shouldReturnCorrectDistance(
      String latitude1,
      String longitude1,
      String latitude2,
      String longitude2,
      double expectedDistance) {
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    double distance =
        distanceCalculator.calculateDistanceBetweenCoordinates(
            latitude1, longitude1, latitude2, longitude2);
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
            0.0) // Laval University to Laval University
        );
  }

  @ParameterizedTest
  @MethodSource("provideDataForisWithinRadius")
  public void isWithinRadius_shouldReturnCorrectAnswer(
      String zipCode, String origin, double maxDistance, double expectedAnswer) {
    DistanceCalculator distanceCalculator = new DistanceCalculator();
    Boolean answer = distanceCalculator.isWithinRadius(zipCode, origin, maxDistance);
    assertEquals(expectedAnswer, answer);
  }

  private static Stream<Arguments> provideDataForisWithinRadius() {
    return Stream.of(
        Arguments.of("10036", "10017", 900, true), // YOTEL NY City to Grand Central Terminal
        Arguments.of(
            "53202",
            "48226",
            326.934371636417,
            true), // The Pfister Hotel, Milhaukee to Beacon Park, Detroit
        Arguments.of("90028", "90045", 20, false), // Hotel Roosevelt LA to LA International Airport
        Arguments.of(
            "90045", "90045", 0.0, true) // LA International Airport to LA International Airport
        );
  }
}
