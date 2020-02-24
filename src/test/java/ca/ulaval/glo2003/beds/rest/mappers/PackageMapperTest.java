package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.domain.helpers.PackageBuilder.aPackage;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Package;
import ca.ulaval.glo2003.beds.domain.PackageNames;
import ca.ulaval.glo2003.beds.rest.PackageResponse;
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
  public void toResponse_shouldMapName() {
    PackageNames expectedName = createPackageName();
    Package bedPackage = aPackage().withName(expectedName).build();

    PackageResponse packageResponse = packageMapper.toResponse(bedPackage);

    assertEquals(expectedName.toString(), packageResponse.getName());
  }

  @Test
  public void toResponse_shouldMapPricePerNight() {
    BigDecimal expectedPricePerNight = createPricePerNight();
    Package bedPackage = aPackage().withPricePerNight(expectedPricePerNight).build();

    PackageResponse packageResponse = packageMapper.toResponse(bedPackage);

    assertEquals(expectedPricePerNight.doubleValue(), packageResponse.getPricePerNight());
  }
}
