package ca.ulaval.glo2003.beds.domain;

import java.util.List;

public class BedStarsCalculator {

  public int calculateStars(Bed bed) {
    double totalPoints =
        calculateTotalPoints(bed.getBedType(), bed.getCleaningFrequency(), bed.getBloodTypes());
    return convertTotalPointsToStars(totalPoints);
  }

  private double calculateTotalPoints(
      BedTypes bedType, CleaningFrequencies cleaningFrequency, List<BloodTypes> bloodTypes) {
    double bedTypeBaseRate = BedTypesBaseRates.get(bedType);
    double cleaningFrequencyDifferential = CleaningFrequenciesDifferentials.get(cleaningFrequency);
    double averageBloodType = calculateAverageBloodType(bloodTypes);
    return bedTypeBaseRate * cleaningFrequencyDifferential * averageBloodType;
  }

  private double calculateAverageBloodType(List<BloodTypes> bloodTypes) {
    double bloodTypesTotalDifferential = 0;

    for (BloodTypes bloodType : bloodTypes) {
      bloodTypesTotalDifferential += BloodTypesDifferentials.get(bloodType);
    }

    return bloodTypesTotalDifferential / bloodTypes.size();
  }

  private int convertTotalPointsToStars(double totalPoints) {
    if (totalPoints < 100) return 1;
    else if (totalPoints < 187.5) return 2;
    else if (totalPoints < 300) return 3;
    else if (totalPoints < 500) return 4;
    else return 5;
  }
}
