package ca.ulaval.glo2003.beds.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageMapperTest {

  private PackageMapper packageMapper;
  private PriceMapper priceMapper;

  @BeforeEach
  public void setUpMapper() {
    priceMapper = mock(PriceMapper.class);
    packageMapper = new PackageMapper(priceMapper);
  }

  @Test
  public void fromRequests_withoutRequest_shouldThrowInvalidPackageException() {
    List<PackageRequest> requests = Collections.emptyList();

    assertThrows(InvalidPackagesException.class, () -> packageMapper.fromRequests(requests));
  }

  @Test
  public void fromRequests_withNullRequest_shouldThrowInvalidPackageException() {
    List<PackageRequest> requests = null;

    assertThrows(InvalidPackagesException.class, () -> packageMapper.fromRequests(requests));
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

    assertThrows(InvalidPackagesException.class, () -> packageMapper.fromRequests(requests));
  }

  @Test
  public void fromRequests_withSingleRequest_shouldMapPricePerNight() {
    double priceValue = 100.0;
    Price expectedPrice = new Price(BigDecimal.valueOf(priceValue));
    Packages packageName = Packages.BLOODTHIRSTY;
    PackageRequest request =
        aPackageRequest().withName(packageName.toString()).withPricePerNight(priceValue).build();
    List<PackageRequest> requests = Collections.singletonList(request);
    when(priceMapper.fromDouble(priceValue)).thenReturn(expectedPrice);

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
    when(priceMapper.fromDouble(priceValue)).thenReturn(expectedPrice);
    when(priceMapper.fromDouble(otherPriceValue)).thenReturn(otherExpectedPrice);

    Map<Packages, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(expectedPrice, pricesPerNight.get(packageName));
    assertEquals(otherExpectedPrice, pricesPerNight.get(otherPackageName));
  }

  @Test
  public void fromRequests_withSamePackageTwice_shouldThrowInvalidPackage() {
    Packages packageName = Packages.BLOODTHIRSTY;
    PackageRequest request1 = aPackageRequest().withName(packageName.toString()).build();
    PackageRequest request2 = aPackageRequest().withName(packageName.toString()).build();
    List<PackageRequest> requests = Arrays.asList(request1, request2);

    assertThrows(InvalidPackagesException.class, () -> packageMapper.fromRequests(requests));
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
    when(priceMapper.toDouble(pricePerNight)).thenReturn(expectedPricePerNight);

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
    when(priceMapper.toDouble(pricePerNight)).thenReturn(expectedPricePerNight);
    when(priceMapper.toDouble(otherPricePerNight)).thenReturn(otherExpectedPricePerNight);

    List<PackageResponse> responses = packageMapper.toResponses(pricesPerNight);

    assertTrue(
        responses.stream()
            .anyMatch(response -> expectedPricePerNight == response.getPricePerNight()));
    assertTrue(
        responses.stream()
            .anyMatch(response -> otherExpectedPricePerNight == response.getPricePerNight()));
  }
}
