package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.time.converters.TimeDateConverter;
import ca.ulaval.glo2003.time.domain.TimeDate;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class ArrivalDateQueryParamAssembler implements BedQueryParamAssembler {

  public static final String ARRIVAL_DATE_PARAM = "arrivalDate";

  private final TimeDateConverter timeDateConverter;

  @Inject
  public ArrivalDateQueryParamAssembler(TimeDateConverter timeDateConverter) {
    this.timeDateConverter = timeDateConverter;
  }

  public BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, List<String>> params) {
    List<String> arrivalDates = params.get(ARRIVAL_DATE_PARAM);

    return arrivalDates != null
        ? builder.withArrivalDate(parseArrivalDate(arrivalDates.get(0)))
        : builder;
  }

  private TimeDate parseArrivalDate(String arrivalDate) {
    return timeDateConverter.fromString(arrivalDate);
  }
}
