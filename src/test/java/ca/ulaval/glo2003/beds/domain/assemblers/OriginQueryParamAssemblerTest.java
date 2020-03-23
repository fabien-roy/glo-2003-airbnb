package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.OriginQueryParamAssembler.ORIGIN_PARAM;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createLocation;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.services.LocationService;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OriginQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static LocationService locationService = mock(LocationService.class);
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private Location origin = createLocation();
  private Map<String, String[]> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new OriginQueryParamAssembler(locationService);
  }

  @BeforeEach
  public void setUpMocks() {
    when(locationService.getLocation(origin.getZipCode())).thenReturn(origin);
    when(queryBuilder.withOrigin(origin)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withOrigin_shouldAssembleBuilder() {
    params.put(ORIGIN_PARAM, new String[] {origin.getZipCode()});

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }
}
