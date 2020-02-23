package ca.ulaval.glo2003.beds.domain;

import java.util.UUID;

public class BedFactory {

  private final BedStarsCalculator starsCalculator;

  public BedFactory(BedStarsCalculator starsCalculator) {
    this.starsCalculator = starsCalculator;
  }

  public Bed create(Bed bed) {
    UUID bedNumber = UUID.randomUUID();
    bed.setNumber(bedNumber);
    bed.setStars(starsCalculator.calculateStars(bed));
    return bed;
  }
}
