package ca.ulaval.glo2003.admin.domain;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;
import java.time.ZonedDateTime;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class ConfigurationTest {

  @Test
  public void instance_shouldReturnSameInstance() {
    Configuration configuration = Configuration.instance();
    Configuration otherConfiguration = Configuration.instance();

    assertSame(configuration, otherConfiguration);
  }

  @Test
  public void getServiceFee_withDefaultValue_shouldReturnNullServiceFee() {
    ServiceFee serviceFee = Configuration.instance().getServiceFee();

    assertNull(serviceFee.toBigDecimal());
  }

  @Test
  public void getLocale_withDefaultValue_shouldReturnLocaleUS() {
    Locale locale = Locale.US;

    Locale actualLocale = Configuration.instance().getLocale();

    assertEquals(locale, actualLocale);
  }

  @Test
  public void getDefaultReportPeriod_withDefaultValue_shouldReturnCurrentYear() {
    TimePeriod period = new TimeYear(ZonedDateTime.now()).toPeriod();

    TimePeriod actualPeriod = Configuration.instance().getDefaultReportPeriod();

    assertEquals(period, actualPeriod);
  }
}
