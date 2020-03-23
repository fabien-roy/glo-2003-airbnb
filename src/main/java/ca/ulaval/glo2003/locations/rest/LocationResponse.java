package ca.ulaval.glo2003.locations.rest;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LocationResponse {

  @SerializedName("post code")
  private String postCode; // Location.zipCode

  private String country;

  @SerializedName("country abbreviation")
  private String countryAbbreviation;

  private List<PlaceResponse> places;

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

  public String getCountry() {
    return country;
  }

  public String getCountryAbbreviation() {
    return countryAbbreviation;
  }

  public List<PlaceResponse> getPlaces() {
    return places;
  }
}
