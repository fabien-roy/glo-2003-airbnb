package ca.ulaval.glo2003.locations.converters;

import static ca.ulaval.glo2003.locations.clients.helpers.LocationResponseBuilder.aLocationResponse;
import static ca.ulaval.glo2003.locations.clients.helpers.PlaceResponseBuilder.aPlaceResponse;
import static ca.ulaval.glo2003.locations.domain.helpers.CoordinatesBuilder.someCoordinates;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createZipCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.locations.clients.LocationResponse;
import ca.ulaval.glo2003.locations.clients.PlaceResponse;
import ca.ulaval.glo2003.locations.domain.Coordinates;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationConverterTest {

  private static LocationConverter locationConverter;
  private static CoordinatesConverter coordinatesConverter = mock(CoordinatesConverter.class);

  private static final ZipCode zipCode = createZipCode();
  private static final Coordinates coordinates = someCoordinates().build();

  private LocationResponse locationResponse;

  @BeforeAll
  public static void setUpConverter() {
    locationConverter = new LocationConverter(coordinatesConverter);
  }

  @BeforeEach
  public void setUpMocks() {
    PlaceResponse placeResponse = aPlaceResponse().build();
    List<PlaceResponse> placeResponses = Collections.singletonList(placeResponse);
    locationResponse =
        aLocationResponse().withPostCode(zipCode.toString()).withPlaces(placeResponses).build();
    when(coordinatesConverter.fromResponse(placeResponse)).thenReturn(coordinates);
  }

  @Test
  public void fromResponse_shouldConvertZipCode() {
    Location location = locationConverter.fromResponse(locationResponse);

    assertEquals(zipCode, location.getZipCode());
  }

  @Test
  public void fromResponse_shouldConvertCoordinates() {
    Location location = locationConverter.fromResponse(locationResponse);

    assertSame(coordinates, location.getCoordinates());
  }
}
