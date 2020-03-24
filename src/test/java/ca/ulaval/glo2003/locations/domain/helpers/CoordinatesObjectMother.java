package ca.ulaval.glo2003.locations.domain.helpers;

import ca.ulaval.glo2003.locations.domain.Latitude;
import ca.ulaval.glo2003.locations.domain.Longitude;
import com.github.javafaker.Faker;

public class CoordinatesObjectMother {

  private CoordinatesObjectMother() {}

  public static Latitude createLatitude() {
    double value = parseDouble(Faker.instance().address().latitude());
    return new Latitude(value);
  }

  public static Longitude createLongitude() {
    double value = parseDouble(Faker.instance().address().longitude());
    return new Longitude(value);
  }

  private static double parseDouble(String value) {
    return Double.parseDouble(value.replace(",", "."));
  }
}
