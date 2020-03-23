package ca.ulaval.glo2003.locations.domain;

import static ca.ulaval.glo2003.locations.domain.helpers.LocationBuilder.aLocation;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createZipCode;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LocationTest {

  private static Location location;
  private static String zipCode = createZipCode();

  private static Location yotelNYCity;
  private static Location grandCentralTerminal;
  private static Location pfisterHotelMilhaukee;
  private static Location beaconParkDetroit;
  private static Location hotelRooseveltLA;
  private static Location laInternationalAirport;

  @BeforeAll
  public static void setUpLocation() {
    location = aLocation().withZipCode(zipCode).build();

    yotelNYCity =
        aLocation().withZipCode("10036").withLongitude(40.759341).withLatitude(-73.995611).build();
    grandCentralTerminal =
        aLocation().withZipCode("10017").withLongitude(46.753077).withLatitude(-73.977154).build();
    pfisterHotelMilhaukee =
        aLocation().withZipCode("53202").withLongitude(43.039643).withLatitude(-87.905641).build();
    beaconParkDetroit =
        aLocation().withZipCode("48226").withLongitude(42.334705).withLatitude(-83.055317).build();
    hotelRooseveltLA =
        aLocation().withZipCode("90028").withLongitude(34.101990).withLatitude(-118.341873).build();
    laInternationalAirport =
        aLocation().withZipCode("90045").withLongitude(33.946834).withLatitude(-118.408961).build();
  }

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotZipCode() {
    String other = "12345";

    boolean result = location.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenZipCodesAreNotEqual() {
    Location other = aLocation().withZipCode("other").build();

    boolean result = location.equals(other);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenZipCodesAreEqual() {
    Location other = aLocation().withZipCode(zipCode).build();

    boolean result = location.equals(other);

    assertTrue(result);
  }

  // TODO : Once Coordinate value object exist, move this logic
  @ParameterizedTest
  @MethodSource("provideDataForCalculateDistanceBetweenCoordinates")
  public void calculateDistanceBetweenCoordinates_shouldReturnCorrectDistance(
      double latitude1,
      double longitude1,
      double latitude2,
      double longitude2,
      double expectedDistance) {
    double distance =
        Location.calculateDistanceBetweenCoordinates(latitude1, longitude1, latitude2, longitude2);
    assertEquals(expectedDistance, distance);
  }

  @ParameterizedTest
  @MethodSource("provideDataForIsWithinRadius_withMaxDistance")
  public void isWithinRadius_withMaxDistance_shouldReturnCorrectAnswer(
      Location location, Location origin, double maxDistance, boolean expectedAnswer) {
    boolean answer = location.isWithinRadius(origin, maxDistance);

    assertEquals(expectedAnswer, answer);
  }

  private static Stream<Arguments> provideDataForCalculateDistanceBetweenCoordinates() {
    return Stream.of(
        Arguments.of(40.759341, -73.995611, 46.753077, -73.977154, 892.0431709382362),
        Arguments.of(43.039643, -87.905641, 42.334705, -83.055317, 326.934371636417),
        Arguments.of(34.101990, -118.341873, 33.946834, -118.408961, 31.281231789505952),
        Arguments.of(46.783506, -71.275291, 46.783506, -71.275291, 0.0));
  }

  private static Stream<Arguments> provideDataForIsWithinRadius_withMaxDistance() {
    return Stream.of(
        Arguments.of(yotelNYCity, grandCentralTerminal, 900, true),
        Arguments.of(pfisterHotelMilhaukee, beaconParkDetroit, 326.934371636417, true),
        Arguments.of(hotelRooseveltLA, laInternationalAirport, 20, false),
        Arguments.of(laInternationalAirport, laInternationalAirport, 0.0, true));
  }
}
