package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.PackageQueryParamAssembler.PACKAGE_NAME_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PackageQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private Packages packageName = Packages.BLOODTHIRSTY;
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new PackageQueryParamAssembler();
  }

  @BeforeEach
  public void setUpMocks() {
    when(queryBuilder.withPackage(packageName)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withPackageName_shouldAssembleBuilder() {
    params.put(PACKAGE_NAME_PARAM, Collections.singletonList(packageName.toString()));

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withInvalidPackageName_shouldThrowInvalidPackageException() {
    params.put(PACKAGE_NAME_PARAM, Collections.singletonList("invalidPackageName"));

    assertThrows(
        InvalidPackageException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }
}
