package ca.ulaval.glo2003.beds.converters;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageConverterTest {

  private static PackageConverter packageConverter;
  private static PriceConverter priceConverter = mock(PriceConverter.class);

  private static Map<Packages, Price> pricesPerNight;
  private static Packages packageName;
  private static Packages otherPackageName;
  private static Price price = createPricePerNight();
  private static Price otherPrice = createPricePerNight();

  private static PackageRequest request;
  private static PackageRequest otherRequest;
  private static List<PackageRequest> requests;
  private static String packageNameValue;
  private static String otherPackageNameValue;
  private static double priceValue = price.getValue().doubleValue();
  private static double otherPriceValue = otherPrice.getValue().doubleValue();
  private static List<PackageResponse> responses;

  @BeforeAll
  public static void setUpConverter() {
    packageConverter = new PackageConverter(priceConverter);
  }

  @BeforeEach
  public void setUpPackages() {
    packageName = Packages.BLOODTHIRSTY;
    otherPackageName = Packages.ALL_YOU_CAN_DRINK;
    packageNameValue = packageName.toString();
    otherPackageNameValue = otherPackageName.toString();

    pricesPerNight = buildPricesPerNight();
    requests = buildPackageRequests();
  }

  @BeforeEach
  public void setUpMocks() {
    when(priceConverter.fromDouble(priceValue)).thenReturn(price);
    when(priceConverter.fromDouble(otherPriceValue)).thenReturn(otherPrice);
    when(priceConverter.toDouble(price)).thenReturn(priceValue);
    when(priceConverter.toDouble(otherPrice)).thenReturn(otherPriceValue);
  }

  private Map<Packages, Price> buildPricesPerNight() {
    Map<Packages, Price> pricesPerNight = new EnumMap<>(Packages.class);
    pricesPerNight.put(packageName, price);
    pricesPerNight.put(otherPackageName, otherPrice);
    return pricesPerNight;
  }

  private List<PackageRequest> buildPackageRequests() {
    request = buildPackageRequest();
    otherRequest = buildOtherPackageRequest();
    return Arrays.asList(request, otherRequest);
  }

  private PackageRequest buildPackageRequest() {
    return aPackageRequest().withName(packageNameValue).withPricePerNight(priceValue).build();
  }

  private PackageRequest buildOtherPackageRequest() {
    return aPackageRequest()
        .withName(otherPackageNameValue)
        .withPricePerNight(otherPriceValue)
        .build();
  }

  @Test
  public void fromRequests_withSingleRequest_shouldMapASinglePricePerNight() {
    requests = requests.subList(0, 1);

    pricesPerNight = packageConverter.fromRequests(requests);

    assertEquals(1, pricesPerNight.keySet().size());
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapMultiplePricesPerNight() {
    pricesPerNight = packageConverter.fromRequests(requests);

    assertEquals(2, pricesPerNight.keySet().size());
  }

  @Test
  public void fromRequests_withSingleRequest_shouldMapName() {
    requests = requests.subList(0, 1);

    pricesPerNight = packageConverter.fromRequests(requests);

    assertTrue(pricesPerNight.containsKey(packageName));
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapNames() {
    pricesPerNight = packageConverter.fromRequests(requests);

    assertTrue(pricesPerNight.containsKey(packageName));
    assertTrue(pricesPerNight.containsKey(otherPackageName));
  }

  @Test
  public void fromRequests_withoutRequest_shouldThrowInvalidPackageException() {
    requests = Collections.emptyList();

    assertThrows(InvalidPackagesException.class, () -> packageConverter.fromRequests(requests));
  }

  @Test
  public void fromRequests_withInvalidPackageName_shouldThrowInvalidPackageException() {
    packageNameValue = "invalidPackageName";
    requests = buildPackageRequests();

    assertThrows(InvalidPackagesException.class, () -> packageConverter.fromRequests(requests));
  }

  @Test
  public void fromRequests_withSingleRequest_shouldMapPricePerNight() {
    pricesPerNight = packageConverter.fromRequests(requests);

    assertEquals(price, pricesPerNight.get(packageName));
  }

  @Test
  public void fromRequests_withMultipleRequests_shouldMapPricesPerNight() {
    pricesPerNight = packageConverter.fromRequests(requests);

    assertEquals(price, pricesPerNight.get(packageName));
    assertEquals(otherPrice, pricesPerNight.get(otherPackageName));
  }

  @Test
  public void fromRequests_withSamePackageTwice_shouldThrowInvalidPackage() {
    otherPackageNameValue = packageNameValue;
    requests = buildPackageRequests();

    assertThrows(InvalidPackagesException.class, () -> packageConverter.fromRequests(requests));
  }

  @Test
  public void toResponses_withSinglePricePerNight_shouldMapASinglePackageResponse() {
    pricesPerNight = Collections.singletonMap(packageName, price);

    responses = packageConverter.toResponses(pricesPerNight);

    assertEquals(1, responses.size());
  }

  @Test
  public void toResponses_withMultiplePricesPerNight_shouldMapMultiplePackageResponses() {
    responses = packageConverter.toResponses(pricesPerNight);

    assertEquals(2, responses.size());
  }

  @Test
  public void toResponses_withSinglePricePerNight_shouldMapName() {
    pricesPerNight = Collections.singletonMap(packageName, price);

    responses = packageConverter.toResponses(pricesPerNight);

    assertEquals(packageNameValue, responses.get(0).getName());
  }

  @Test
  public void toResponses_withMultiplePricesPerNight_shouldMapNames() {
    responses = packageConverter.toResponses(pricesPerNight);

    assertTrue(
        responses.stream().anyMatch(response -> packageNameValue.equals(response.getName())));
    assertTrue(
        responses.stream().anyMatch(response -> otherPackageNameValue.equals(response.getName())));
  }

  @Test
  public void toResponses_withSinglePricePerNight_shouldMapPricePerNight() {
    pricesPerNight = Collections.singletonMap(packageName, price);

    responses = packageConverter.toResponses(pricesPerNight);

    assertEquals(priceValue, responses.get(0).getPricePerNight());
  }

  @Test
  public void toResponses_withMultiplePricesPerNight_shouldMapPricesPerNight() {
    responses = packageConverter.toResponses(pricesPerNight);

    assertTrue(responses.stream().anyMatch(response -> priceValue == response.getPricePerNight()));
    assertTrue(
        responses.stream().anyMatch(response -> otherPriceValue == response.getPricePerNight()));
  }
}
