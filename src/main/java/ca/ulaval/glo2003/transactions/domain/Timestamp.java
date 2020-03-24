package ca.ulaval.glo2003.transactions.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Timestamp {

  private LocalDateTime value;

  public static Timestamp now() {
    return new Timestamp(LocalDateTime.now());
  }

  public static Timestamp inDays(int days) {
    LocalDateTime value = LocalDate.now().plusDays(days).atTime(LocalTime.MIDNIGHT);
    return new Timestamp(value);
  }

  public Timestamp(LocalDateTime value) {
    this.value = value;
  }

  public LocalDateTime getValue() {
    return value;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    Timestamp bookingDate = (Timestamp) object;

    return value.equals(bookingDate.getValue());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
