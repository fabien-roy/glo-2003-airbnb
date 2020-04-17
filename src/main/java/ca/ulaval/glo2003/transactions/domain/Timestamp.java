package ca.ulaval.glo2003.transactions.domain;

import java.time.*;

public class Timestamp {

  public static final LocalTime LOCAL_TIME = LocalTime.MAX;
  public static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

  private Instant value;

  public static Timestamp now() {
    return new Timestamp(LocalDateTime.now().toInstant(ZONE_OFFSET));
  }

  public Timestamp(Instant value) {
    this.value = value;
  }

  public Timestamp(LocalDate localDate) {
    value = localDate.atTime(LOCAL_TIME).toInstant(ZONE_OFFSET);
  }

  public Instant toInstant() {
    return value;
  }

  // TODO : Test Timestamp.toLocalDate()
  public LocalDate toLocalDate() {
    return value.atZone(ZONE_OFFSET).toLocalDate();
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    Timestamp timestamp = (Timestamp) object;

    return value.equals(timestamp.toInstant());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
