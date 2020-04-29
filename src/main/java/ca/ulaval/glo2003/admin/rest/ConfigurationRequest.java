package ca.ulaval.glo2003.admin.rest;

import ca.ulaval.glo2003.admin.domain.ServiceFee;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ConfigurationRequest {

  private ServiceFee serviceFee;

  public ServiceFee getServiceFee() {
    return serviceFee;
  }

  @JsonProperty("serviceFee")
  @JsonDeserialize(using = ServiceFeeDeserializer.class)
  public void setServiceFee(ServiceFee serviceFee) {
    this.serviceFee = serviceFee;
  }
}
