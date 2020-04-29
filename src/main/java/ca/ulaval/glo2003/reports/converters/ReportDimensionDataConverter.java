package ca.ulaval.glo2003.reports.converters;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionData;
import ca.ulaval.glo2003.reports.rest.ReportDimensionDataResponse;
import java.util.ArrayList;
import java.util.List;

// TODO : Test this!
public class ReportDimensionDataConverter {

  public List<ReportDimensionDataResponse> toResponses(List<ReportDimensionData> dimensions) {
    List<ReportDimensionDataResponse> responses = new ArrayList<>();
    dimensions.forEach(dimension -> responses.add(toResponse(dimension)));
    return responses;
  }

  private ReportDimensionDataResponse toResponse(ReportDimensionData data) {
    return null; // TODO
  }
}
