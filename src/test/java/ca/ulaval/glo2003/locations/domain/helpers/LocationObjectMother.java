package ca.ulaval.glo2003.locations.domain.helpers;

import com.github.javafaker.Faker;

public class LocationObjectMother {

  private LocationObjectMother() {}

  public static String createZipCode() {
    return Faker.instance().address().zipCode();
  }

  public static double createLongitude() {
    return parseDouble(Faker.instance().address().longitude());
  }

  public static double createLatitude() {
    return parseDouble(Faker.instance().address().latitude());
  }

  private static double parseDouble(String value) {
    return Double.parseDouble(value.replace(",", "."));
  }
}
