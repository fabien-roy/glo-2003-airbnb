package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.rest.serializers.PackageNameDeserializer;
import ca.ulaval.glo2003.transactions.rest.serializers.PriceDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PackageRequest {

  @JsonDeserialize(using = PackageNameDeserializer.class)
  private String name;

  @JsonDeserialize(using = PriceDeserializer.class)
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
