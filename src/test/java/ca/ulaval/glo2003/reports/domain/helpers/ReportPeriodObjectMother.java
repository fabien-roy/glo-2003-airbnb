package ca.ulaval.glo2003.reports.domain.helpers;

import static ca.ulaval.glo2003.interfaces.domain.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationBuilder.aLocation;

import ca.ulaval.glo2003.beds.domain.BedTypes;
import ca.ulaval.glo2003.beds.domain.BloodTypes;
import ca.ulaval.glo2003.beds.domain.CleaningFrequencies;
import ca.ulaval.glo2003.locations.domain.Location;
import com.github.javafaker.Faker;
import java.util.Collections;
import java.util.List;

public class ReportPeriodObjectMother {

  private ReportPeriodObjectMother() {}

  public static Location createLocation() {
    return aLocation().build();
  }

  public static BedTypes createBedType() {
    return randomEnum(BedTypes.class);
  }

  public static CleaningFrequencies createCleaningFrequency() {
    return randomEnum(CleaningFrequencies.class);
  }

  public static List<BloodTypes> createBloodTypes() {
    return Collections.singletonList(randomEnum(BloodTypes.class));
  }

  public static int createCapacity() {
    return Faker.instance().number().numberBetween(1, 1000);
  }
}
