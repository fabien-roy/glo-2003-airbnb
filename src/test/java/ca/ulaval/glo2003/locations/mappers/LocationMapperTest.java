package ca.ulaval.glo2003.locations.mappers;

import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createLongitude;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createZipCode;
import static ca.ulaval.glo2003.locations.infrastructure.helpers.LocationResponseBuilder.aLocationResponse;
import static ca.ulaval.glo2003.locations.infrastructure.helpers.PlaceResponseBuilder.aPlaceResponse;
import static org.junit.jupiter.api.Assertions.*;

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

  private static final String zipCode = createZipCode();
  private static final double longitude = createLongitude();
  private static final double latitude = createLongitude();

  private LocationResponse locationResponse;

  @BeforeAll
  public static void setUpMapper() {
    locationMapper = new LocationMapper();
  }

  @BeforeEach
  public void setUpMocks() {
    PlaceResponse placeResponse =
        aPlaceResponse()
            .withLongitude(Double.toString(longitude))
            .withLatitude(Double.toString(latitude))
            .build();
    List<PlaceResponse> placeResponses = Collections.singletonList(placeResponse);
    locationResponse = aLocationResponse().withPostCode(zipCode).withPlaces(placeResponses).build();
  }

  @Test
  public void fromResponse_shouldMapZipCode() {
    Location location = locationMapper.fromResponse(locationResponse);

    assertEquals(zipCode, location.getZipCode());
  }

  @Test
  public void fromResponse_shouldMapLongitude() {
    Location location = locationMapper.fromResponse(locationResponse);

    assertEquals(longitude, location.getLongitude());
  }

  @Test
  public void fromResponse_shouldMapLatitude() {
    Location location = locationMapper.fromResponse(locationResponse);

    assertEquals(latitude, location.getLatitude());
  }
}
