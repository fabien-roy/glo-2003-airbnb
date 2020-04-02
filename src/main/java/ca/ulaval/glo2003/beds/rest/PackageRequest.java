package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.rest.serializers.PackageNameDeserializer;
import ca.ulaval.glo2003.beds.rest.serializers.PricePerNightDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PackageRequest {

  @JsonDeserialize(using = PackageNameDeserializer.class)
  private String name;

  @JsonDeserialize(using = PricePerNightDeserializer.class)
  private double pricePerNight = 0;

  public PackageRequest() {
    // Empty constructor for parsing
  }

  public PackageRequest(String name, Double pricePerNight) {
    this.name = name;

    if (pricePerNight != null) this.pricePerNight = pricePerNight;
  }

  public String getName() {
    return name;
  }

  public double getPricePerNight() {
    return pricePerNight;
  }
}
