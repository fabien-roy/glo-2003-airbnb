package ca.ulaval.glo2003.locations.domain;

import static ca.ulaval.glo2003.locations.domain.helpers.CoordinatesBuilder.someCoordinates;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationBuilder.aLocation;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LocationTest {

  private static Location location;
  private static Location origin;
  private static Coordinates coordinates = mock(Coordinates.class);
  private static Coordinates originCoordinates = someCoordinates().build();

  private static final double maxDistance = 10;

  @BeforeAll
  public static void setUpLocations() {
    location = aLocation().withCoordinates(coordinates).build();
    origin = aLocation().withCoordinates(originCoordinates).build();
  }

  @Test
  public void isWithinRadius_withDistanceLowerThanMax_shouldReturnTrue() {
    when(coordinates.calculateDistance(originCoordinates)).thenReturn(maxDistance - 1);

    boolean result = location.isWithinRadius(origin, maxDistance);

    assertTrue(result);
  }

  @Test
  public void isWithinRadius_withDistanceEqualToMax_shouldReturnTrue() {
    when(coordinates.calculateDistance(originCoordinates)).thenReturn(maxDistance);

    boolean result = location.isWithinRadius(origin, maxDistance);

    assertTrue(result);
  }

  @Test
  public void isWithinRadius_withDistanceHigherThanMax_shouldReturnFalse() {
    when(coordinates.calculateDistance(originCoordinates)).thenReturn(maxDistance + 1);

    boolean result = location.isWithinRadius(origin, maxDistance);

    assertFalse(result);
  }
}
