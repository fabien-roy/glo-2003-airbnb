package ca.ulaval.glo2003.locations.infrastructure.helpers;

import com.github.javafaker.Faker;

public class PlaceResponseObjectMother {

  private PlaceResponseObjectMother() {}

  public static String createPlaceName() {
    return Faker.instance().address().fullAddress();
  }

  public static String createLongitude() {
    return Faker.instance().address().longitude();
  }

  public static String createState() {
    return Faker.instance().address().state();
  }

  public static String createStateAbbreviation() {
    return Faker.instance().address().stateAbbr();
  }

  public static String createLatitude() {
    return Faker.instance().address().latitude();
  }
}
