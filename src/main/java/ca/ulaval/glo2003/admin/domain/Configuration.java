package ca.ulaval.glo2003.admin.domain;

import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;
import java.time.ZonedDateTime;
import java.util.Locale;

public class Configuration {

  private static Configuration instance;
  private ServiceFee serviceFee;
  private Locale locale;
  private TimePeriod defaultReportPeriod;

  private Configuration() {
    serviceFee = new ServiceFee(null);
    locale = Locale.US;
    defaultReportPeriod = new TimeYear(ZonedDateTime.now()).toPeriod();
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

  public TimePeriod getDefaultReportPeriod() {
    return defaultReportPeriod;
  }

  public void setDefaultReportPeriod(TimePeriod defaultReportPeriod) {
    this.defaultReportPeriod = defaultReportPeriod;
  }
}
