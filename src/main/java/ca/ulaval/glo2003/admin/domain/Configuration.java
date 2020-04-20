package ca.ulaval.glo2003.admin.domain;

public class Configuration {

  private static Configuration instance;
  private ServiceFee serviceFee;

  private Configuration() {
    serviceFee = new ServiceFee(null);
  }

  public static Configuration instance() {
    if (instance == null) instance = new Configuration();
    return instance;
  }

  public ServiceFee getServiceFee() {
    return serviceFee;
  }

  public void setServiceFee(ServiceFee serviceFee) {
    this.serviceFee = serviceFee;
  }
}
