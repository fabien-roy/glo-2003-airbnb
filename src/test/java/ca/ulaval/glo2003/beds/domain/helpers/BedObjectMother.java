package ca.ulaval.glo2003.beds.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageBuilder.aPackage;
import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.CapacityMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.util.*;

public class BedObjectMother {

  // TODO : This is also used in BedRequestObjectMother. Duplicated code is bad.
  private static final FakeValuesService fakeValuesService =
      new FakeValuesService(new Locale("en-US"), new RandomService());

  private BedObjectMother() {}

  public static UUID createBedNumber() {
    return UUID.randomUUID();
  }

  public static String createOwnerPublicKey() {
    return fakeValuesService.regexify(BedMapper.OWNER_PUBLIC_KEY_PATTERN);
  }

  public static String createZipCode() {
    return Faker.instance().address().zipCode();
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

  public static int createCapacity(BedTypes bedType) {
    int maxCapacity;
    maxCapacity = CapacityMapper.getMaxCapacity(bedType);
    return Faker.instance().number().numberBetween(1, maxCapacity);
  }

  public static List<Package> createPackages() {
    return Collections.singletonList(aPackage().build());
  }
}
