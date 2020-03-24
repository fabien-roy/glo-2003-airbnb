package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.mappers.PriceJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PackageRequest {

  private String name;

  @JsonSerialize(using = PriceJsonSerializer.class)
  private Double pricePerNight;

  public PackageRequest() {
    // Empty constructor for parsing
  }

  public PackageRequest(String name, double pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(Double pricePerNight) {
    this.pricePerNight = pricePerNight;
  }
}
