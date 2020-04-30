package ca.ulaval.glo2003.reports.converters;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.rest.ReportDimensionDataResponse;
import ca.ulaval.glo2003.reports.rest.ReportMetricDataResponse;
import ca.ulaval.glo2003.reports.rest.ReportPeriodDataResponse;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ReportPeriodDataConverter {

  private final ReportDimensionDataConverter reportDimensionDataConverter;
  private final ReportMetricDataConverter reportMetricDataConverter;

  @Inject
  public ReportPeriodDataConverter(
      ReportDimensionDataConverter reportDimensionDataConverter,
      ReportMetricDataConverter reportMetricDataConverter) {
    this.reportDimensionDataConverter = reportDimensionDataConverter;
    this.reportMetricDataConverter = reportMetricDataConverter;
  }

  public List<ReportPeriodDataResponse> toResponses(List<ReportPeriodData> data) {
    List<ReportPeriodDataResponse> responses = new ArrayList<>();
    data.forEach(singleData -> responses.add(toResponse(singleData)));
    return responses;
  }

  private ReportPeriodDataResponse toResponse(ReportPeriodData data) {
    List<ReportDimensionDataResponse> dimensions =
        reportDimensionDataConverter.toResponses(data.getDimensions());
    List<ReportMetricDataResponse> metrics =
        reportMetricDataConverter.toResponses(data.getMetrics());
    return new ReportPeriodDataResponse(dimensions, metrics);
  }
}
