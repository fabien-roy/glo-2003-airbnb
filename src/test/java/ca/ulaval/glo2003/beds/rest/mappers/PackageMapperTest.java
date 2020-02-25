package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageBuilder.aPackage;
import static ca.ulaval.glo2003.beds.rest.helpers.PackageRequestBuilder.aPackageRequest;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother;
import ca.ulaval.glo2003.beds.rest.PackageRequest;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.beds.rest.helpers.PackageRequestObjectMother;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageMapperTest {

  private PackageMapper packageMapper;

  @BeforeEach
  public void setUpMapper() {
    packageMapper = new PackageMapper();
  }

  @Test
  public void fromRequest_shouldMapName() {
    String expectedName = PackageRequestObjectMother.createPackageName();
    PackageRequest packageRequest = aPackageRequest().withName(expectedName).build();

    Package testPackage = packageMapper.fromRequest(packageRequest);

    assertEquals(expectedName, testPackage.getName().toString());
  }

  @Test
  public void fromRequest_withInvalidPackageName_shouldThrowInvalidPackageException() {
    String invalidName = "invalidName";
    PackageRequest packageRequest = aPackageRequest().withName(invalidName).build();

    assertThrows(InvalidPackageException.class, () -> packageMapper.fromRequest(packageRequest));
  }

  @Test
  public void fromRequest_shouldMapPricePerNight() {
    double expectedPrice = PackageRequestObjectMother.createPricePerNight();
    PackageRequest packageRequest = aPackageRequest().withPricePerNight(expectedPrice).build();

    Package testPackage = packageMapper.fromRequest(packageRequest);

    assertEquals(expectedPrice, testPackage.getPricePerNight().doubleValue());
  }

  @Test
  public void toResponse_shouldMapName() {
    PackageNames expectedName = PackageObjectMother.createPackageName();
    Package bedPackage = aPackage().withName(expectedName).build();

    PackageResponse packageResponse = packageMapper.toResponse(bedPackage);

    assertEquals(expectedName.toString(), packageResponse.getName());
  }

  @Test
  public void toResponse_shouldMapPricePerNight() {
    BigDecimal expectedPricePerNight = PackageObjectMother.createPricePerNight();
    Package bedPackage = aPackage().withPricePerNight(expectedPricePerNight).build();

    PackageResponse packageResponse = packageMapper.toResponse(bedPackage);

    assertEquals(expectedPricePerNight.doubleValue(), packageResponse.getPricePerNight());
  }
}
