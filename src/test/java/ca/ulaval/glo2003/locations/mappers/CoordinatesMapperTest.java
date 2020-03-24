package ca.ulaval.glo2003.locations.mappers;

import static ca.ulaval.glo2003.locations.domain.helpers.CoordinatesObjectMother.createLatitude;
import static ca.ulaval.glo2003.locations.domain.helpers.CoordinatesObjectMother.createLongitude;
import static ca.ulaval.glo2003.locations.infrastructure.helpers.PlaceResponseBuilder.aPlaceResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.locations.domain.Coordinates;
import ca.ulaval.glo2003.locations.domain.Latitude;
import ca.ulaval.glo2003.locations.domain.Longitude;
import ca.ulaval.glo2003.locations.infrastructure.PlaceResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordinatesMapperTest {

  private static CoordinatesMapper coordinatesMapper;

  private static final Longitude longitude = createLongitude();
  private static final Latitude latitude = createLatitude();

  private PlaceResponse placeResponse;

  @BeforeAll
  public static void setUpMapper() {
    coordinatesMapper = new CoordinatesMapper();
  }

  @BeforeEach
  public void setUpMocks() {
    placeResponse =
        aPlaceResponse()
            .withLongitude(Double.toString(longitude.getValue()))
            .withLatitude(Double.toString(latitude.getValue()))
            .build();
  }

  @Test
  public void fromResponse_shouldMapLongitude() {
    Coordinates coordinates = coordinatesMapper.fromResponse(placeResponse);

    assertEquals(longitude, coordinates.getLongitude());
  }

  @Test
  public void fromResponse_shouldMapLatitude() {
    Coordinates coordinates = coordinatesMapper.fromResponse(placeResponse);

    assertEquals(latitude, coordinates.getLatitude());
  }
}
