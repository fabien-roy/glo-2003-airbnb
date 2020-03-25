package ca.ulaval.glo2003.locations.infrastructure.helpers;

import com.github.javafaker.Faker;

public class LocationResponseObjectMother {

  private LocationResponseObjectMother() {}

  public static String createPostCode() {
    return Faker.instance().address().zipCode();
  }

  public static String createCountry() {
    return Faker.instance().country().name();
  }

  public static String createCountryAbbreviation() {
    return Faker.instance().country().countryCode2();
  }
}
