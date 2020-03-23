package ca.ulaval.glo2003.beds.domain.assemblers;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import java.util.Map;

public interface BedQueryParamAssembler {

  BedQueryBuilder assemble(BedQueryBuilder builder, Map<String, String[]> params);
}
