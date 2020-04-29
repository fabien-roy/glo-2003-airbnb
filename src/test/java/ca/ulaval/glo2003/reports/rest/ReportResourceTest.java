package ca.ulaval.glo2003.reports.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.interfaces.rest.QueryParamMapConverter;
import ca.ulaval.glo2003.reports.services.ReportService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class ReportResourceTest {

  private static ReportResource reportResource;
  private static ReportService reportService = mock(ReportService.class);
  private static ReportMapper reportMapper = mock(ReportMapper.class);
  private static QueryParamMapConverter queryParamMapConverter = mock(QueryParamMapConverter.class);

  private static Request request = mock(Request.class);
  private static Response response = mock(Response.class);
  private static ReportPeriodResponse reportPeriodResponse = mock(ReportPeriodResponse.class);
  private static ReportPeriodResponse otherReportPeriodResponse = mock(ReportPeriodResponse.class);
  private static Map<String, List<String>> queryParamsMap = mock(Map.class);
  private static HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

  @BeforeAll
  public static void setUpResource() {
    reportResource = new ReportResource(reportService, reportMapper, queryParamMapConverter);
  }

  @BeforeEach
  public void setUpMocks() {
    reset(response);
    String requestBody = "requestBody";
    String queryString = "queryString";
    when(request.body()).thenReturn(requestBody);
    when(request.raw()).thenReturn(httpServletRequest);
    when(httpServletRequest.getQueryString()).thenReturn(queryString);
    when(queryParamMapConverter.fromString(queryString)).thenReturn(queryParamsMap);
    when(reportService.getAll(queryParamsMap))
        .thenReturn(Arrays.asList(reportPeriodResponse, otherReportPeriodResponse));
  }

  @Test
  public void getAll_shouldReturnReportPeriods() {
    List<ReportPeriodResponse> reportResponses =
        (List<ReportPeriodResponse>) reportResource.getAll(request, response);

    assertEquals(2, reportResponses.size());
    assertTrue(reportResponses.contains(reportPeriodResponse));
    assertTrue(reportResponses.contains(otherReportPeriodResponse));
  }

  @Test
  public void getAll_shouldSetOKAsHttpStatus() {
    reportResource.getAll(request, response);

    verify(response).status(HttpStatus.OK_200);
  }
}
