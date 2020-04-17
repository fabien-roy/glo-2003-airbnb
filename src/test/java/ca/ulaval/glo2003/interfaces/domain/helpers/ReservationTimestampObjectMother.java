package ca.ulaval.glo2003.interfaces.domain.helpers;

import ca.ulaval.glo2003.interfaces.domain.ReservationTimestamp;
import com.github.javafaker.Faker;
import java.time.Instant;

public class ReservationTimestampObjectMother {

  private ReservationTimestampObjectMother() {}

  public static ReservationTimestamp createTimestamp() {
    Instant value = Faker.instance().date().birthday().toInstant();
    return new ReservationTimestamp(value);
  }
}
