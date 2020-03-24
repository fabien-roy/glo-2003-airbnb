package ca.ulaval.glo2003.locations.domain.helpers;

import ca.ulaval.glo2003.locations.domain.ZipCode;
import com.github.javafaker.Faker;

public class LocationObjectMother {

  private LocationObjectMother() {}

  public static ZipCode createZipCode() {
    String value = Faker.instance().address().zipCode().substring(0, 5);
    return new ZipCode(value);
  }
}
