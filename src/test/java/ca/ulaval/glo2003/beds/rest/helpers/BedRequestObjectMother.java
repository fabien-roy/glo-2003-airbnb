package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BedRequestObjectMother {

  private static final FakeValuesService fakeValuesService =
      new FakeValuesService(new Locale("en-US"), new RandomService());

  private BedRequestObjectMother() {}

  public static String createOwnerPublicKey() {
    return fakeValuesService.regexify(BedMapper.OWNER_PUBLIC_KEY_PATTERN);
  }

  public static String createZipCode() {
    return fakeValuesService.regexify(BedMapper.ZIP_CODE_PATTERN);
  }

  public static String createBedType() {
    return randomEnum(BedTypes.class).toString();
  }

  public static String createCleaningFrequency() {
    return randomEnum(CleaningFrequencies.class).toString();
  }

  public static List<String> createBloodTypes() {
    return Collections.singletonList(randomEnum(BloodTypes.class).toString());
  }

  public static int createCapacity() {
    return Faker.instance().number().numberBetween(1, 1000);
  }

  public static List<PackageRequest> createPackages() {
    return Collections.singletonList(
        new PackageRequest(createPackageName(), createPackagePricePerNight()));
  }

  private static String createPackageName() {
    return randomEnum(Packages.class).toString();
  }

  private static double createPackagePricePerNight() {
    return Faker.instance().number().randomDouble(2, 100, 1000);
  }
}
