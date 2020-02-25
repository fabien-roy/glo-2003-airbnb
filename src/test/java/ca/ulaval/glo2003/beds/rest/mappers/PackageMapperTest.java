package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageBuilder.aPackage;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidPackageException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    Map<PackageNames, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(1, pricesPerNight.size());
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapMultiplePricesPerNight() {
    PackageRequest request = aPackageRequest().build();
    PackageRequest otherRequest = aPackageRequest().build();
    List<PackageRequest> requests = Arrays.asList(request, otherRequest);

    Map<PackageNames, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(2, pricesPerNight.size());
  }

  @Test
  public void fromRequests_withSingleRequest_shouldMapName() {
    PackageNames expectedPackage = PackageNames.BLOODTHIRSTY;
    String packageName = expectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    List<PackageRequest> requests = Collections.singletonList(request);

    Map<PackageNames, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertTrue(pricesPerNight.containsKey(expectedPackage));
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapNames() {
    PackageNames expectedPackage = PackageNames.BLOODTHIRSTY;
    PackageNames otherExpectedPackage = PackageNames.SWEET_TOOTH;
    String packageName = expectedPackage.toString();
    String otherPackageName = otherExpectedPackage.toString();
    PackageRequest request = aPackageRequest().withName(packageName).build();
    PackageRequest otherRequest = aPackageRequest().withName(otherPackageName).build();
    List<PackageRequest> requests = Arrays.asList(request, otherRequest);

    Map<PackageNames, Price> pricesPerNight = packageMapper.fromRequests(requests);

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
    PackageNames packageName = PackageNames.BLOODTHIRSTY;
    PackageRequest request =
        aPackageRequest().withName(packageName.toString()).withPricePerNight(priceValue).build();
    List<PackageRequest> requests = Collections.singletonList(request);

    Map<PackageNames, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(expectedPrice, pricesPerNight.get(packageName));
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapPricesPerNight() {
    double priceValue = 100.0;
    double otherPriceValue = 200.0;
    Price expectedPrice = new Price(BigDecimal.valueOf(priceValue));
    Price otherExpectedPrice = new Price(BigDecimal.valueOf(otherPriceValue));
    PackageNames packageName = PackageNames.BLOODTHIRSTY;
    PackageNames otherPackageName = PackageNames.SWEET_TOOTH;
    PackageRequest request =
        aPackageRequest().withName(packageName.toString()).withPricePerNight(priceValue).build();
    PackageRequest otherRequest =
        aPackageRequest()
            .withName(otherPackageName.toString())
            .withPricePerNight(otherPriceValue)
            .build();
    List<PackageRequest> requests = Arrays.asList(request, otherRequest);

    Map<PackageNames, Price> pricesPerNight = packageMapper.fromRequests(requests);

    assertEquals(expectedPrice, pricesPerNight.get(packageName));
    assertEquals(otherExpectedPrice, pricesPerNight.get(otherPackageName));
  }

  // TODO : Update toResponse tests

  @Test
  public void toResponse_shouldMapName() {
    PackageNames expectedName = PackageObjectMother.createPackageName();
    Package bedPackage = aPackage().withName(expectedName).build();

    PackageResponse packageResponse = packageMapper.toResponse(bedPackage);

    assertEquals(expectedName.toString(), packageResponse.getName());
  }

  @Test
  public void toResponse_shouldMapPricePerNight() {
    Price expectedPricePerNight = PackageObjectMother.createPricePerNight();
    Package bedPackage = aPackage().withPricePerNight(expectedPricePerNight).build();

    PackageResponse packageResponse = packageMapper.toResponse(bedPackage);

    assertEquals(
        expectedPricePerNight.getValue().doubleValue(), packageResponse.getPricePerNight());
  }
}
