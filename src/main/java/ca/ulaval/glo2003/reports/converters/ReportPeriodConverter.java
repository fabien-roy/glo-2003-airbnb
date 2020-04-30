package ca.ulaval.glo2003.reports.converters;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.reports.rest.ReportPeriodDataResponse;
import ca.ulaval.glo2003.reports.rest.ReportPeriodResponse;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ReportPeriodConverter {

  private final ReportPeriodDataConverter reportPeriodDataConverter;

  @Inject
  public ReportPeriodConverter(ReportPeriodDataConverter reportPeriodDataConverter) {
    this.reportPeriodDataConverter = reportPeriodDataConverter;
  }

  public List<ReportPeriodResponse> toResponses(List<ReportPeriod> periods) {
    List<ReportPeriodResponse> responses = new ArrayList<>();
    periods.forEach(period -> responses.add(toResponse(period)));
    return responses;
  }

  private ReportPeriodResponse toResponse(ReportPeriod period) {
    List<ReportPeriodDataResponse> data = reportPeriodDataConverter.toResponses(period.getData());
    return new ReportPeriodResponse(period.getName(), data);
  }
}
