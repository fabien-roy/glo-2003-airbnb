package ca.ulaval.glo2003.locations.mappers;

import static ca.ulaval.glo2003.locations.domain.helpers.CoordinatesBuilder.someCoordinates;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createZipCode;
import static ca.ulaval.glo2003.locations.infrastructure.helpers.LocationResponseBuilder.aLocationResponse;
import static ca.ulaval.glo2003.locations.infrastructure.helpers.PlaceResponseBuilder.aPlaceResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.locations.domain.Coordinates;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.infrastructure.LocationResponse;
import ca.ulaval.glo2003.locations.infrastructure.PlaceResponse;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationMapperTest {

  private static LocationMapper locationMapper;
  private static CoordinatesMapper coordinatesMapper = mock(CoordinatesMapper.class);

  private static final String zipCode = createZipCode();
  private static final Coordinates coordinates = someCoordinates().build();

  private LocationResponse locationResponse;

  @BeforeAll
  public static void setUpMapper() {
    locationMapper = new LocationMapper(coordinatesMapper);
  }

  @BeforeEach
  public void setUpMocks() {
    PlaceResponse placeResponse = aPlaceResponse().build();
    List<PlaceResponse> placeResponses = Collections.singletonList(placeResponse);
    locationResponse = aLocationResponse().withPostCode(zipCode).withPlaces(placeResponses).build();
    when(coordinatesMapper.fromResponse(placeResponse)).thenReturn(coordinates);
  }

  @Test
  public void fromResponse_shouldMapZipCode() {
    Location location = locationMapper.fromResponse(locationResponse);

    assertEquals(zipCode, location.getZipCode());
  }

  @Test
  public void fromResponse_shouldMapCoordinates() {
    Location location = locationMapper.fromResponse(locationResponse);

    assertSame(coordinates, location.getCoordinates());
  }
}
