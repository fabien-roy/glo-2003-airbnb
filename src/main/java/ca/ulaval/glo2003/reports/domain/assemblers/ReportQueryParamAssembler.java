package ca.ulaval.glo2003.reports.domain.assemblers;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import java.util.List;
import java.util.Map;

public interface ReportQueryParamAssembler {

  ReportQueryBuilder assemble(ReportQueryBuilder builder, Map<String, List<String>> params);
}
