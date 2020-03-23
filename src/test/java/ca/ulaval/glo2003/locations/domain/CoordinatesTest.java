package ca.ulaval.glo2003.locations.domain;

import static ca.ulaval.glo2003.locations.domain.helpers.CoordinatesBuilder.someCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CoordinatesTest {

  private static Coordinates coordinates1 =
      someCoordinates().withLatitude(40.759341).withLongitude(-73.995611).build();
  private static Coordinates coordinates2 =
      someCoordinates().withLatitude(46.753077).withLongitude(-73.977154).build();
  private static Coordinates coordinates3 =
      someCoordinates().withLatitude(43.039643).withLongitude(-87.905641).build();
  private static Coordinates coordinates4 =
      someCoordinates().withLatitude(42.334705).withLongitude(-83.055317).build();
  private static Coordinates coordinates5 =
      someCoordinates().withLatitude(34.101990).withLongitude(-118.341873).build();
  private static Coordinates coordinates6 =
      someCoordinates().withLatitude(33.946834).withLongitude(-118.408961).build();
  private static Coordinates coordinates7 =
      someCoordinates().withLatitude(46.783506).withLongitude(-71.275291).build();

  @ParameterizedTest
  @MethodSource("provideDataForCalculateDistance")
  public void calculateDistance_shouldReturnCorrectDistance(
      Coordinates coordinates, Coordinates otherCoordinates, double distance) {
    double actualDistance = coordinates.calculateDistance(otherCoordinates);

    assertEquals(distance, actualDistance);
  }

  private static Stream<Arguments> provideDataForCalculateDistance() {
    return Stream.of(
        Arguments.of(coordinates1, coordinates2, 892.0431709382362),
        Arguments.of(coordinates3, coordinates4, 326.934371636417),
        Arguments.of(coordinates5, coordinates6, 31.281231789505952),
        Arguments.of(coordinates7, coordinates7, 0.0));
  }
}
