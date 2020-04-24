package ca.ulaval.glo2003.admin.domain;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ConfigurationTest {

  @Test
  public void instance_shouldReturnSameInstance() {
    Configuration configuration = Configuration.instance();
    Configuration otherConfiguration = Configuration.instance();

    assertSame(configuration, otherConfiguration);
  }

  // TODO : Test defaults
}
