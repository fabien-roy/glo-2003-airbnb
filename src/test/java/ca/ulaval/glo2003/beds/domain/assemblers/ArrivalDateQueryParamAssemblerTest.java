package ca.ulaval.glo2003.beds.domain.assemblers;

import static ca.ulaval.glo2003.beds.domain.assemblers.ArrivalDateQueryParamAssembler.ARRIVAL_DATE_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.bookings.converters.BookingDateConverter;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrivalDateQueryParamAssemblerTest {

  private static BedQueryParamAssembler queryAssembler;
  private static BookingDateConverter bookingDateConverter = mock(BookingDateConverter.class);
  private static BedQueryBuilder queryBuilder = mock(BedQueryBuilder.class);
  private static BedQueryBuilder assembledQueryBuilder = mock(BedQueryBuilder.class);

  private BookingDate arrivalDate = BookingDate.now();
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new ArrivalDateQueryParamAssembler(bookingDateConverter);
  }

  @BeforeEach
  public void setUpMocks() {
    when(bookingDateConverter.fromString(arrivalDate.toString())).thenReturn(arrivalDate);
    when(queryBuilder.withArrivalDate(arrivalDate)).thenReturn(assembledQueryBuilder);
  }

  @Test
  public void assemble_withoutArrivalDate_shouldReturnSameBuilder() {
    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(queryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withArrivalDate_shouldAssembleBuilder() {
    params.put(ARRIVAL_DATE_PARAM, Collections.singletonList(arrivalDate.toString()));

    BedQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }
}
