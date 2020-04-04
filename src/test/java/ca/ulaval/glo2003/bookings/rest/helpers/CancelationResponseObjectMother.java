package ca.ulaval.glo2003.bookings.rest.helpers;

import com.github.javafaker.Faker;

public class CancelationResponseObjectMother {

  private CancelationResponseObjectMother() {}

  public static double createRefundAmount() {
    return Faker.instance().number().randomDouble(2, 100, 1000);
  }
}
