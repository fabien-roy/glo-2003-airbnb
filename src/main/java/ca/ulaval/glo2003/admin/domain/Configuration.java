package ca.ulaval.glo2003.admin.domain;

import java.util.Locale;

public class Configuration {

  private static Configuration instance;
  private ServiceFee serviceFee;
  private Locale locale;

  private Configuration() {
    serviceFee = new ServiceFee(null);
    locale = Locale.US;
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

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }
}
