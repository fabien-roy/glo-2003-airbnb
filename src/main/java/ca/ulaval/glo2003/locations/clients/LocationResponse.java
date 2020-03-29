package ca.ulaval.glo2003.locations.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LocationResponse {

  @JsonProperty("post code")
  private String postCode;

  private String country;

  @JsonProperty("country abbreviation")
  private String countryAbbreviation;

  private List<PlaceResponse> places;

  public LocationResponse() {
    // Empty constructor for parsing
  }

  public LocationResponse(
      String postCode, String country, String countryAbbreviation, List<PlaceResponse> places) {
    this.postCode = postCode;
    this.country = country;
    this.countryAbbreviation = countryAbbreviation;
    this.places = places;
  }

  public String getPostCode() {
    return postCode;
  }

  public List<PlaceResponse> getPlaces() {
    return places;
  }
}
