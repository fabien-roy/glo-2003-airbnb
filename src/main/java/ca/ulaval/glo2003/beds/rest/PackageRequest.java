package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.rest.serializers.PackageNameDeserializer;
import ca.ulaval.glo2003.beds.rest.serializers.PricePerNightDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PackageRequest {
  private String name;
  private double pricePerNight = 0;

  public String getName() {
    return name;
  }

  @JsonProperty("name")
  @JsonDeserialize(using = PackageNameDeserializer.class)
  public void setName(String name) {
    this.name = name;
  }

  public double getPricePerNight() {
    return pricePerNight;
  }

  @JsonProperty("pricePerNight")
  @JsonDeserialize(using = PricePerNightDeserializer.class)
  public void setPricePerNight(Double pricePerNight) {
    if (pricePerNight != null) this.pricePerNight = pricePerNight;
  }
}
