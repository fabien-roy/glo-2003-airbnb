package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BedStarsCalculatorTest {

  @ParameterizedTest
  @MethodSource("provideConditionsForCalculateStars")
  public void calculateStars_shouldReturnCorrectNumberOfStars(
      BedTypes bedType,
      CleaningFrequencies cleaningFrequency,
      List<BloodTypes> bloodTypes,
      int expectedStars) {
    Bed bed =
        aBed()
            .withBedType(bedType)
            .withCleaningFrequency(cleaningFrequency)
            .withBloodTypes(bloodTypes)
            .build();
    BedStarsCalculator bedStarsCalculator = new BedStarsCalculator();

    int stars = bedStarsCalculator.calculateStars(bed);

    assertEquals(expectedStars, stars);
  }

  private static Stream<Arguments> provideConditionsForCalculateStars() {
    return Stream.of(
        Arguments.of(
            BedTypes.LATEX,
            CleaningFrequencies.WEEKLY,
            Collections.singletonList(BloodTypes.O_MINUS),
            3),
        Arguments.of(
            BedTypes.LATEX,
            CleaningFrequencies.WEEKLY,
            Collections.singletonList(BloodTypes.B_PLUS),
            1),
        Arguments.of(
            BedTypes.MEMORY_FOAM,
            CleaningFrequencies.ANNUAL,
            Collections.singletonList(BloodTypes.AB_MINUS),
            2),
        Arguments.of(
            BedTypes.SPRINGS,
            CleaningFrequencies.NEVER,
            Arrays.asList(BloodTypes.O_MINUS, BloodTypes.O_PLUS, BloodTypes.A_PLUS),
            5),
        Arguments.of(
            BedTypes.LATEX,
            CleaningFrequencies.ANNUAL,
            Arrays.asList(BloodTypes.O_MINUS, BloodTypes.B_MINUS),
            4));
  }
}
