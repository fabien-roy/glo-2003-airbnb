package ca.ulaval.glo2003.beds.rest;

import static spark.Spark.get;
import static spark.Spark.post;

import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.interfaces.rest.QueryParamMapConverter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class BedResource implements RouteGroup {

  public static final String BED_PATH = "/beds";

  private final BedService bedService;
  private final BedMapper bedMapper;
  private final QueryParamMapConverter queryParamMapConverter;

  @Inject
  public BedResource(
      BedService bedService, BedMapper bedMapper, QueryParamMapConverter queryParamMapConverter) {
    this.bedService = bedService;
    this.bedMapper = bedMapper;
    this.queryParamMapConverter = queryParamMapConverter;
  }

  @Override
  public void addRoutes() {
    post("", this::add);
    get("", this::getAll, bedMapper::writeValueAsString);
    get("/:number", this::getByNumber, bedMapper::writeValueAsString);
  }

  public Object add(Request request, Response response) throws IOException {
    BedRequest bedRequest = bedMapper.readValue(request.body(), BedRequest.class);

    String bedNumber = bedService.add(bedRequest);
    String bedPath = String.format("%s/%s", BED_PATH, bedNumber);

    response.status(HttpStatus.CREATED_201);
    response.header(HttpHeader.LOCATION.asString(), bedPath);

    return bedPath;
  }

  public Object getAll(Request request, Response response) {
    Map<String, List<String>> queryParamMap =
        queryParamMapConverter.fromString(request.raw().getQueryString());
    List<BedResponse> bedResponses = bedService.getAllResponses(queryParamMap);

    response.status(HttpStatus.OK_200);
    return bedResponses;
  }

  public Object getByNumber(Request request, Response response) {
    String bedNumber = request.params("number");
    BedResponse bedResponse = bedService.getResponse(bedNumber);

    response.status(HttpStatus.OK_200);
    return bedResponse;
  }
}
