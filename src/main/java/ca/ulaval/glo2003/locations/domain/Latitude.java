package ca.ulaval.glo2003.locations.domain;

public class Latitude {

  private static final double KM_PER_DEGREE = 110.574;

  private double value;

  public Latitude(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public double toKm() {
    return value * KM_PER_DEGREE;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    Latitude latitude = (Latitude) object;

    return value == latitude.getValue();
  }

  @Override
  public int hashCode() {
    return Double.hashCode(value);
  }
}
