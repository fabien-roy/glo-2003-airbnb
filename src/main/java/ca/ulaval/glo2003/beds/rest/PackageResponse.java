package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.transactions.rest.serializers.PriceSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PackageResponse {

  private String name;

  @JsonSerialize(using = PriceSerializer.class)
  private double pricePerNight;

  public PackageResponse(String name, double pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
  }

  public String getName() {
    return name;
  }

  public double getPricePerNight() {
    return pricePerNight;
  }
}
