package ca.ulaval.glo2003.locations.domain.helpers;

import com.github.javafaker.Faker;

public class LocationObjectMother {

  private LocationObjectMother() {}

  public static String createZipCode() {
    return Faker.instance().address().zipCode();
  }
}
