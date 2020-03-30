package ca.ulaval.glo2003.transactions.domain;

import java.time.*;

public class Timestamp {

  public static final LocalTime LOCAL_TIME = LocalTime.MIDNIGHT;
  public static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

  private Instant value;

  public static Timestamp now() {
    return new Timestamp(LocalDateTime.now().toInstant(ZONE_OFFSET));
  }

  public static Timestamp inDays(int days) {
    Instant value = LocalDate.now().plusDays(days).atTime(LOCAL_TIME).toInstant(ZONE_OFFSET);
    return new Timestamp(value);
  }

  public Timestamp(Instant value) {
    this.value = value;
  }

  public Instant getValue() {
    return value;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    Timestamp timestamp = (Timestamp) object;

    return value.equals(timestamp.getValue());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
