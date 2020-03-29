package ca.ulaval.glo2003.locations.clients.helpers;

import static ca.ulaval.glo2003.locations.clients.helpers.LocationResponseObjectMother.*;
import static ca.ulaval.glo2003.locations.clients.helpers.PlaceResponseBuilder.aPlaceResponse;

import ca.ulaval.glo2003.locations.clients.LocationResponse;
import ca.ulaval.glo2003.locations.clients.PlaceResponse;
import java.util.Collections;
import java.util.List;

public class LocationResponseBuilder {

  private LocationResponseBuilder() {}

  private String DEFAULT_POST_CODE = createPostCode();
  private String postCode = DEFAULT_POST_CODE;

  private String DEFAULT_COUNTRY = createCountry();
  private String country = DEFAULT_COUNTRY;

  private String DEFAULT_COUNTRY_ABBREVIATION = createCountryAbbreviation();
  private String countryAbbreviation = DEFAULT_COUNTRY_ABBREVIATION;

  private List<PlaceResponse> DEFAULT_PLACES = Collections.singletonList(aPlaceResponse().build());
  private List<PlaceResponse> places = DEFAULT_PLACES;

  public static LocationResponseBuilder aLocationResponse() {
    return new LocationResponseBuilder();
  }

  public LocationResponseBuilder withPostCode(String postCode) {
    this.postCode = postCode;
    return this;
  }

  public LocationResponseBuilder withPlaces(List<PlaceResponse> places) {
    this.places = places;
    return this;
  }

  public LocationResponse build() {
    return new LocationResponse(postCode, country, countryAbbreviation, places);
  }
}
