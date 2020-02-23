package ca.ulaval.glo2003.beds.domain;

import java.util.UUID;

public class BedFactory {

  private final BedStarsCalculator bedStarsCalculator;

  public BedFactory(BedStarsCalculator bedStarsCalculator) {
    this.bedStarsCalculator = bedStarsCalculator;
  }

  public Bed create(Bed bed) {
    UUID bedNumber = UUID.randomUUID();
    bed.setNumber(bedNumber);
    bed.setStars(bedStarsCalculator.calculateStars(bed));
    return bed;
  }
}
