package ca.ulaval.glo2003.locations.converters;

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

class CoordinatesConverterTest {

  private static CoordinatesConverter coordinatesConverter;

  private static final Longitude longitude = createLongitude();
  private static final Latitude latitude = createLatitude();

  private PlaceResponse placeResponse;

  @BeforeAll
  public static void setUpConverter() {
    coordinatesConverter = new CoordinatesConverter();
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
  public void fromResponse_shouldConvertLongitude() {
    Coordinates coordinates = coordinatesConverter.fromResponse(placeResponse);

    assertEquals(longitude, coordinates.getLongitude());
  }

  @Test
  public void fromResponse_shouldConvertLatitude() {
    Coordinates coordinates = coordinatesConverter.fromResponse(placeResponse);

    assertEquals(latitude, coordinates.getLatitude());
  }
}
