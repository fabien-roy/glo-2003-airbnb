package ca.ulaval.glo2003.beds.rest.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.rest.mappers.LocationMapper;
import ca.ulaval.glo2003.locations.rest.services.LocationService;
import com.github.javafaker.Faker;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class BedRequestObjectMother {

  private BedRequestObjectMother() {}
  // TODO verifier si correct
  private static final LocationService locationService = new LocationService(new LocationMapper());

  public static String createOwnerPublicKey() {
    return createPublicKey().getValue();
  }

  public static Location createLocation() throws IOException {
    return locationService.getLocation(Faker.instance().address().zipCode());
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

  public static String createLodgingMode() {
    return randomEnum(LodgingModes.class).toString();
  }
}
