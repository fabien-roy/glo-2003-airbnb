package ca.ulaval.glo2003.time.domain;

import ca.ulaval.glo2003.admin.domain.Configuration;
import java.util.Locale;
import org.junit.jupiter.api.BeforeAll;

public abstract class TimeCalendarTest {

  @BeforeAll
  public static void setUpConfiguration() {
    Configuration.instance().setLocale(Locale.US);
  }
}
