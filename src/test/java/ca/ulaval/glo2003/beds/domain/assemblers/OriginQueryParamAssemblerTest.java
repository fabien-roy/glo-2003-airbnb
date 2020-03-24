package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.OriginQueryParamAssembler.ORIGIN_PARAM;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createZipCode;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.infrastructure.ZippopotamusClient;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OriginQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static ZippopotamusClient zippopotamusClient = mock(ZippopotamusClient.class);
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private ZipCode origin = createZipCode();
  private Map<String, String[]> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new OriginQueryParamAssembler(zippopotamusClient);
  }

  @BeforeEach
  public void setUpMocks() {
    when(zippopotamusClient.validateZipCode(origin.getValue())).thenReturn(origin);
    when(queryBuilder.withOrigin(origin)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withOrigin_shouldAssembleBuilder() {
    params.put(ORIGIN_PARAM, new String[] {origin.getValue()});

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }
}
