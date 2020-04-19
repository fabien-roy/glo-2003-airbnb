package ca.ulaval.glo2003.admin.domain;

public class Configuration {

  private ServiceFee serviceFee;

  public Configuration(ServiceFee serviceFee) {
    this.serviceFee = serviceFee;
  }

  public ServiceFee getServiceFee() {
    return serviceFee;
  }
}
