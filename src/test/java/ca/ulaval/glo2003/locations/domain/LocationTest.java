package ca.ulaval.glo2003.locations.domain;

import static ca.ulaval.glo2003.locations.domain.helpers.LocationBuilder.aLocation;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

class LocationTest {

  private static Location location;

  @BeforeAll
  public static void setUpLocation() {
    location = aLocation().build();
  }

  // TODO : Test isWithinRadius
}
