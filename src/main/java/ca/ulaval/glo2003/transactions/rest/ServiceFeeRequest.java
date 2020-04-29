package ca.ulaval.glo2003.transactions.rest;

import ca.ulaval.glo2003.transactions.rest.serializers.ServiceFeeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ServiceFeeRequest {

  private double serviceFee = 0;

  public double getServiceFee() {
    return serviceFee;
  }

  @JsonProperty("serviceFee")
  @JsonDeserialize(using = ServiceFeeDeserializer.class)
  public void setServiceFee(Double serviceFee) {
    if (serviceFee != null) this.serviceFee = serviceFee;
  }
}
