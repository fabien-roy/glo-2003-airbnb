package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createLocation;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static ca.ulaval.glo2003.interfaces.helpers.Randomizer.randomEnum;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.converters.PackageConverter;
import ca.ulaval.glo2003.beds.converters.validators.PackageValidator;
import ca.ulaval.glo2003.beds.exceptions.AllYouCanDrinkDependencyException;
import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.beds.exceptions.SweetToothDependencyException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class BedFactoryTest {

  // TODO : Refactor this test class

  private static BedFactory bedFactory;
  private static PriceConverter priceConverter = mock(PriceConverter.class);
  private static Set<PackageValidator> validators =
      Collections.singleton(mock(PackageValidator.class));
  private static PackageValidator defaultValidator = mock(PackageValidator.class);
  private static PackageConverter packageConverter;

  private List<PackageRequest> packageRequests = getPackageRequest();
  private List<PackageRequest> otherPackageRequests = getPackageRequest();
  private Map<Packages, Price> packages = packageConverter.fromRequests(packageRequests);
  private Map<Packages, Price> otherPackages = packageConverter.fromRequests(otherPackageRequests);
  private Bed bed = aBed().withPricesPerNights(packages).build();
  private Bed otherBed = aBed().withPricesPerNights(otherPackages).build();
  private Location location = createLocation();

  @BeforeAll
  public static void setUpFactory() {
    bedFactory = new BedFactory();
    packageConverter = new PackageConverter(priceConverter, validators, defaultValidator);
  }

  @Test
  public void create_shouldSetBedNumber() {
    bed = bedFactory.create(bed, location);

    assertNotNull(bed.getNumber());
  }

  @Test
  public void create_shouldSetZipCode() {
    bed = bedFactory.create(bed, location);

    assertEquals(location, bed.getLocation());
  }

  @Test
  public void create_shouldSetDifferentBedNumbers() {
    bed = bedFactory.create(bed, location);
    otherBed = bedFactory.create(otherBed, location);

    assertNotEquals(bed.getNumber(), otherBed.getNumber());
  }

  @ParameterizedTest
  @EnumSource(BedTypes.class)
  public void create_withExceedingCapacity_shouldThrowExceedingAccommodationCapacityException(
      BedTypes bedType) {
    int maxCapacity = BedTypesCapacities.get(bedType);
    bed =
        aBed()
            .withBedType(bedType)
            .withCapacity(maxCapacity + 1)
            .withPricesPerNights(packages)
            .build();

    assertThrows(
        ExceedingAccommodationCapacityException.class, () -> bedFactory.create(bed, location));
  }

  private List<PackageRequest> getPackageRequest() {
    Packages randPackage = randomEnum(Packages.class);
    List<String> requestPackagesNames = new ArrayList<>();
    do {
      requestPackagesNames.add(randPackage.toString());
      randPackage = OutdatedPackagesDependencies.getDependency(randPackage);
    } while (randPackage != null);
    return requestPackagesNames.stream()
        .map(s -> aPackageRequest().withName(s).build())
        .collect(Collectors.toList());
  }
}
