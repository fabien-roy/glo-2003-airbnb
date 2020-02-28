package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.domain.PackagesDependency;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.AllYouCanDrinkDependencyException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.beds.rest.exceptions.SweetToothDependencyException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PackageMapperTest {

  private PackageMapper packageMapper;

  @BeforeEach
  public void setUpMapper() {
    packageMapper = new PackageMapper();
  }

  @Test
  public void fromRequests_withSingleRequest_shouldMapASinglePricePerNight() {
    PackageRequest request = aPackageRequest().build();
    List<PackageRequest> requests = Collections.singletonList(request);

    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(1, pricesPerNight.keySet().size());
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapMultiplePricesPerNight() {
    PackageRequest request = aPackageRequest().withName(Packages.SWEET_TOOTH.toString()).build();
    PackageRequest otherRequest =
        aPackageRequest().withName(Packages.BLOODTHIRSTY.toString()).build();
    List<PackageRequest> requests = Arrays.asList(request, otherRequest);

    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(2, pricesPerNight.keySet().size());
  }

  @Test
  public void fromRequests_withSingleRequest_shouldMapName() {
    Packages expectedPackage = Packages.BLOODTHIRSTY;
    String packageName = expectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    List<PackageRequest> requests = Collections.singletonList(request);

    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertTrue(pricesPerNight.containsKey(expectedPackage));
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapNames() {
    Packages expectedPackage = Packages.BLOODTHIRSTY;
    Packages otherExpectedPackage = Packages.SWEET_TOOTH;
    String packageName = expectedPackage.toString();
    String otherPackageName = otherExpectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    PackageRequest otherRequest = aPackageRequest().withName(otherPackageName).build();
    List<PackageRequest> requests = Arrays.asList(request, otherRequest);

    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertTrue(pricesPerNight.containsKey(expectedPackage));
    assertTrue(pricesPerNight.containsKey(otherExpectedPackage));
  }

  @Test
  public void fromRequests_withInvalidPackageName_shouldThrowInvalidPackageException() {
    String invalidPackageName = "invalidPackageName";
    PackageRequest request = aPackageRequest().withName(invalidPackageName).build();
    List<PackageRequest> requests = Collections.singletonList(request);

    assertThrows(InvalidPackageException.class, () -> packageMapper.fromRequests(requests));
  }

  @Test
  public void fromRequests_withSingleRequest_shouldMapPricePerNight() {
    double priceValue = 100.0;
    Price expectedPrice = new Price(BigDecimal.valueOf(priceValue));
    Packages packageName = Packages.BLOODTHIRSTY;
    PackageRequest request =
        aPackageRequest().withName(packageName.toString()).withPricePerNight(priceValue).build();
    List<PackageRequest> requests = Collections.singletonList(request);

    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(expectedPrice, pricesPerNight.get(packageName));
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapPricesPerNight() {
    double priceValue = 100.0;
    double otherPriceValue = 200.0;
    Price expectedPrice = new Price(BigDecimal.valueOf(priceValue));
    Price otherExpectedPrice = new Price(BigDecimal.valueOf(otherPriceValue));
    Packages packageName = Packages.BLOODTHIRSTY;
    Packages otherPackageName = Packages.SWEET_TOOTH;
    PackageRequest request =
        aPackageRequest().withName(packageName.toString()).withPricePerNight(priceValue).build();
    PackageRequest otherRequest =
        aPackageRequest()
            .withName(otherPackageName.toString())
            .withPricePerNight(otherPriceValue)
            .build();
    List<PackageRequest> requests = Arrays.asList(request, otherRequest);

    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(expectedPrice, pricesPerNight.get(packageName));
    assertEquals(otherExpectedPrice, pricesPerNight.get(otherPackageName));
  }

  @Test
  public void toResponses_withSinglePricePerNight_shouldMapASinglePackageResponse() {
    Map<Packages, Price> pricesPerNight =
        Collections.singletonMap(createPackageName(), createPricePerNight());

    List<PackageResponse> responses = packageMapper.toResponses(pricesPerNight);

    assertEquals(1, responses.size());
  }

  @Test
  public void toResponses_withMultiplePricesPerNight_shouldMapMultiplePackageResponses() {
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
    pricesPerNight.put(Packages.SWEET_TOOTH, createPricePerNight());
    pricesPerNight.put(Packages.BLOODTHIRSTY, createPricePerNight());

    List<PackageResponse> responses = packageMapper.toResponses(pricesPerNight);

    assertEquals(2, responses.size());
  }

  @Test
  public void toResponses_withSinglePricePerNight_shouldMapName() {
    Packages packageName = createPackageName();
    String expectedPackageName = packageName.toString();
    Map<Packages, Price> pricesPerNight =
        Collections.singletonMap(packageName, createPricePerNight());

    List<PackageResponse> responses = packageMapper.toResponses(pricesPerNight);

    assertEquals(expectedPackageName, responses.get(0).getName());
  }

  @Test
  public void toResponses_withMultiplePricesPerNight_shouldMapNames() {
    Packages packageName = createPackageName();
    Packages otherPackageName = createPackageName();
    String expectedPackageName = packageName.toString();
    String otherExpectedPackageName = otherPackageName.toString();
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
    pricesPerNight.put(packageName, createPricePerNight());
    pricesPerNight.put(otherPackageName, createPricePerNight());

    List<PackageResponse> responses = packageMapper.toResponses(pricesPerNight);

    assertTrue(
        responses.stream().anyMatch(response -> expectedPackageName.equals(response.getName())));
    assertTrue(
        responses.stream()
            .anyMatch(response -> otherExpectedPackageName.equals(response.getName())));
  }

  @Test
  public void toResponses_withSinglePricePerNight_shouldMapPricePerNight() {
    Price pricePerNight = createPricePerNight();
    double expectedPricePerNight = pricePerNight.getValue().doubleValue();
    Map<Packages, Price> pricesPerNight =
        Collections.singletonMap(createPackageName(), pricePerNight);

    List<PackageResponse> responses = packageMapper.toResponses(pricesPerNight);

    assertEquals(expectedPricePerNight, responses.get(0).getPricePerNight());
  }

  @Test
  public void toResponses_withMultiplePricesPerNight_shouldMapPricesPerNight() {
    Price pricePerNight = createPricePerNight();
    Price otherPricePerNight = createPricePerNight();
    double expectedPricePerNight = pricePerNight.getValue().doubleValue();
    double otherExpectedPricePerNight = otherPricePerNight.getValue().doubleValue();
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
    pricesPerNight.put(Packages.SWEET_TOOTH, pricePerNight);
    pricesPerNight.put(Packages.BLOODTHIRSTY, otherPricePerNight);

    List<PackageResponse> responses = packageMapper.toResponses(pricesPerNight);

    assertTrue(
        responses.stream()
            .anyMatch(response -> expectedPricePerNight == response.getPricePerNight()));
    assertTrue(
        responses.stream()
            .anyMatch(response -> otherExpectedPricePerNight == response.getPricePerNight()));
  }

  @ParameterizedTest
  @EnumSource(Packages.class)
  public void create_withExceedingCapacity_shouldThrowExceedingAccommodationCapacityException(
      Packages testPackage) {
    List<String> requestPackagesNames = new ArrayList<String>();
    do {
      requestPackagesNames.add(testPackage.toString());
      testPackage = PackagesDependency.getDependency(testPackage);
    } while (testPackage != null);
    List<PackageRequest> packageRequests =
        requestPackagesNames.stream()
            .map(s -> aPackageRequest().withName(s).build())
            .collect(Collectors.toList());

    assertDoesNotThrow(() -> packageMapper.fromRequests(packageRequests));
  }

  @Test
  public void create_withoutAllYouCanDrinkDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    Packages expectedPackage = Packages.ALL_YOU_CAN_DRINK;
    String packageName = expectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    List<PackageRequest> requests = Collections.singletonList(request);

    assertThrows(
        AllYouCanDrinkDependencyException.class, () -> packageMapper.fromRequests(requests));
  }

  @Test
  public void create_withoutSweetToothDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    Packages expectedPackage = Packages.BLOODTHIRSTY;
    Packages otherExpectedPackage = Packages.SWEET_TOOTH;
    String packageName = expectedPackage.toString();
    String otherPackageName = otherExpectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    PackageRequest otherRequest = aPackageRequest().withName(otherPackageName).build();
    List<PackageRequest> requests = Arrays.asList(request, otherRequest);

    assertThrows(SweetToothDependencyException.class, () -> packageMapper.fromRequests(requests));
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
    List<PackageRequest> requests = Arrays.asList(request, otherRequest);

    assertThrows(
        AllYouCanDrinkDependencyException.class, () -> packageMapper.fromRequests(requests));
  }

  @Test
  public void create_withOnlySweetToothDependencies_shouldThrowCantOfferAllYouCanDrinkPackage() {
    Packages expectedPackage = Packages.SWEET_TOOTH;
    String packageName = expectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    List<PackageRequest> requests = Collections.singletonList(request);

    assertThrows(SweetToothDependencyException.class, () -> packageMapper.fromRequests(requests));
  }
}
