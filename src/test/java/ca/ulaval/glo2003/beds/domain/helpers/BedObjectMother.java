package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.LodgingModeBuilder.aLodgingMode;
import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationBuilder.aLocation;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.locations.domain.Location;
import com.github.javafaker.Faker;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class BedObjectMother {

  private BedObjectMother() {}

  public static UUID createBedNumber() {
    return UUID.randomUUID();
  }

  public static PublicKey createOwnerPublicKey() {
    return createPublicKey();
  }

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

  public static LodgingMode createLodgingMode() {
    return aLodgingMode().build();
  }
}
