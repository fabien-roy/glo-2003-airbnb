package ca.ulaval.glo2003.locations.clients;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceResponse {

  @JsonProperty("place name")
  private String placeName;

  private String longitude;
  private String state;

  @JsonProperty("state abbreviation")
  private String stateAbbreviation;

  private String latitude;

  public PlaceResponse() {
    // Empty constructor for parsing
  }

  public PlaceResponse(
      String placeName, String longitude, String state, String stateAbbreviation, String latitude) {
    this.placeName = placeName;
    this.longitude = longitude;
    this.state = state;
    this.stateAbbreviation = stateAbbreviation;
    this.latitude = latitude;
  }

  public String getPlaceName() {
    return placeName;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getState() {
    return state;
  }

  public String getStateAbbreviation() {
    return stateAbbreviation;
  }

  public String getLatitude() {
    return latitude;
  }
}
