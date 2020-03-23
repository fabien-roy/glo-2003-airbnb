package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.mappers.PriceJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PackageResponse {

  private String name;

  @JsonSerialize(using = PriceJsonSerializer.class)
  private Double pricePerNight;

  public PackageResponse(String name, Double pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
  }

  public String getName() {
    return name;
  }

  public Double getPricePerNight() {
    return pricePerNight;
  }
}
