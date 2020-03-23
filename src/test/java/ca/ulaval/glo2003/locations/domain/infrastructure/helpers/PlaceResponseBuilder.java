package ca.ulaval.glo2003.locations.domain.infrastructure.helpers;

import static ca.ulaval.glo2003.locations.domain.infrastructure.helpers.PlaceResponseObjectMother.*;

import ca.ulaval.glo2003.locations.infrastructure.PlaceResponse;

public class PlaceResponseBuilder {

  private PlaceResponseBuilder() {}

  private String DEFAULT_PLACE_NAME = createPlaceName();
  private String placeName = DEFAULT_PLACE_NAME;

  private String DEFAULT_LONGITUDE = createLongitude();
  private String longitude = DEFAULT_LONGITUDE;

  private String DEFAULT_STATE = createState();
  private String state = DEFAULT_STATE;

  private String DEFAULT_STATE_ABBREVIATION = createStateAbbreviation();
  private String stateAbbreviation = DEFAULT_STATE_ABBREVIATION;

  private String DEFAULT_LATITUDE = createLatitude();
  private String latitude = DEFAULT_LATITUDE;

  public static PlaceResponseBuilder aPlaceResponse() {
    return new PlaceResponseBuilder();
  }

  public PlaceResponseBuilder withLongitude(String longitude) {
    this.longitude = longitude;
    return this;
  }

  public PlaceResponseBuilder withLatitude(String latitude) {
    this.latitude = latitude;
    return this;
  }

  public PlaceResponse build() {
    return new PlaceResponse(placeName, longitude, state, stateAbbreviation, latitude);
  }
}
