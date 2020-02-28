package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestBuilder.aBedRequest;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.exceptions.AllYouCanDrinkDependencyException;
import ca.ulaval.glo2003.beds.rest.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.beds.rest.exceptions.SweetToothDependencyException;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.PackageMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class BedFactoryTest {

  BedFactory bedFactory;
  BedMapper bedMapper;

  @BeforeEach
  public void setUpFactory() {
    bedFactory = new BedFactory();
    bedMapper = new BedMapper(new PackageMapper());
  }

  @Test
  public void create_shouldSetBedNumber() {
    Bed bed = aBed().build();

    bed = bedFactory.create(bed);

    assertNotNull(bed.getNumber());
  }

  @Test
  public void create_shouldSetDifferentBedNumbers() {
    Bed bed = aBed().build();
    Bed otherBed = aBed().build();

    bed = bedFactory.create(bed);
    otherBed = bedFactory.create(otherBed);

    assertNotEquals(bed.getNumber(), otherBed.getNumber());
  }

  @ParameterizedTest
  @EnumSource(BedTypes.class)
  public void create_withExceedingCapacity_shouldThrowExceedingAccommodationCapacityException(
      BedTypes bedType) {
    int maxCapacity = BedTypesCapacities.get(bedType);
    Bed bed = aBed().withBedType(bedType).withCapacity(maxCapacity + 1).build();

    assertThrows(ExceedingAccommodationCapacityException.class, () -> bedFactory.create(bed));
  }

  @ParameterizedTest
  @EnumSource(Packages.class)
  public void create_withDependencies_shouldThrowNoThrow(Packages testPackage) {
    List<String> requestPackagesNames = new ArrayList<String>();
    do {
      requestPackagesNames.add(testPackage.toString());
      testPackage = PackagesDependency.getDependency(testPackage);
    } while (testPackage != null);
    List<PackageRequest> packageRequests =
        requestPackagesNames.stream()
            .map(s -> aPackageRequest().withName(s).build())
            .collect(Collectors.toList());
    BedRequest bedRequest = aBedRequest().withPackages(packageRequests).build();

    assertDoesNotThrow(() -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void create_withoutAllYouCanDrinkDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    Packages expectedPackage = Packages.ALL_YOU_CAN_DRINK;
    String packageName = expectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    List<PackageRequest> packageRequests = Collections.singletonList(request);
    BedRequest bedRequest = aBedRequest().withPackages(packageRequests).build();

    assertThrows(AllYouCanDrinkDependencyException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void create_withoutSweetToothDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    Packages expectedPackage = Packages.BLOODTHIRSTY;
    Packages otherExpectedPackage = Packages.SWEET_TOOTH;
    String packageName = expectedPackage.toString();
    String otherPackageName = otherExpectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    PackageRequest otherRequest = aPackageRequest().withName(otherPackageName).build();
    List<PackageRequest> packageRequests = Arrays.asList(request, otherRequest);
    BedRequest bedRequest = aBedRequest().withPackages(packageRequests).build();

    assertThrows(SweetToothDependencyException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void
      create_withSWAmdAYCNWithoutAYCDDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    Packages expectedPackage = Packages.ALL_YOU_CAN_DRINK;
    Packages otherExpectedPackage = Packages.SWEET_TOOTH;
    String packageName = expectedPackage.toString();
    String otherPackageName = otherExpectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    PackageRequest otherRequest = aPackageRequest().withName(otherPackageName).build();
    List<PackageRequest> packageRequests = Arrays.asList(request, otherRequest);
    BedRequest bedRequest = aBedRequest().withPackages(packageRequests).build();

    assertThrows(AllYouCanDrinkDependencyException.class, () -> bedMapper.fromRequest(bedRequest));
  }

  @Test
  public void create_withOnlySweetToothDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    Packages expectedPackage = Packages.SWEET_TOOTH;
    String packageName = expectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    List<PackageRequest> packageRequests = Collections.singletonList(request);
    BedRequest bedRequest = aBedRequest().withPackages(packageRequests).build();

    assertThrows(SweetToothDependencyException.class, () -> bedMapper.fromRequest(bedRequest));
  }
}
