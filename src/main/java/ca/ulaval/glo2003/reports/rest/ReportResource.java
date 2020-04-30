package ca.ulaval.glo2003.reports.rest;

import static spark.Spark.get;

import ca.ulaval.glo2003.interfaces.rest.QueryParamMapConverter;
import ca.ulaval.glo2003.reports.services.ReportService;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class ReportResource implements RouteGroup {

  public static final String REPORT_PATH = "/admin/reports";

  private final ReportService reportService;
  private final ReportMapper reportMapper;
  private final QueryParamMapConverter queryParamMapConverter;

  @Inject
  public ReportResource(
      ReportService reportService,
      ReportMapper reportMapper,
      QueryParamMapConverter queryParamMapConverter) {
    this.reportService = reportService;
    this.reportMapper = reportMapper;
    this.queryParamMapConverter = queryParamMapConverter;
  }

  @Override
  public void addRoutes() {
    get("", this::getAll, reportMapper::writeValueAsString);
  }

  public Object getAll(Request request, Response response) {
    Map<String, List<String>> queryParamMap =
        queryParamMapConverter.fromString(request.raw().getQueryString());
    List<ReportPeriodResponse> transactionResponses = reportService.getAll(queryParamMap);

    response.status(HttpStatus.OK_200);
    return transactionResponses;
  }
}
