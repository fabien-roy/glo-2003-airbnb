package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.rest.BedQueryMapBuilder.BED_TYPE_PARAM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.rest.BedQueryMapBuilder;
import java.util.Map;
import org.junit.jupiter.api.Test;
import spark.Request;

public class BedQueryMapBuilderTest {

  private BedQueryMapBuilder bedQueryMapBuilder = new BedQueryMapBuilder();

  @Test
  public void buildQueryMap_withBedType_shouldReturnMapWithBedType() {
    String bedType = "bedType";
    String[] bedTypeArray = new String[] {bedType};
    Request request = mock(Request.class);
    when(request.queryParams(BED_TYPE_PARAM)).thenReturn(bedType);

    Map<String, String[]> actualMap = bedQueryMapBuilder.buildQueryMap(request);

    assertEquals(bedTypeArray, actualMap.get(BED_TYPE_PARAM));
  }
}
