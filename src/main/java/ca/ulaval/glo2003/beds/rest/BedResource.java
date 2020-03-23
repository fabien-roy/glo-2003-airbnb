package ca.ulaval.glo2003.beds.rest;

import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.*;
import static spark.Spark.*;

import ca.ulaval.glo2003.beds.services.BedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
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

  @Inject
  public BedResource(BedService bedService) {
    this.bedService = bedService;
  }

  @Override
  public void addRoutes() {
    post("", this::add);
    get("", this::getAll, new ObjectMapper()::writeValueAsString);
    get("/:number", this::getByNumber, new ObjectMapper()::writeValueAsString);
  }

  public Object add(Request request, Response response) throws JsonProcessingException {
    BedRequest bedRequest;

    bedRequest = new ObjectMapper().readValue(request.body(), BedRequest.class);

    String bedNumber = bedService.add(bedRequest);
    String bedPath = String.format("%s/%s", BED_PATH, bedNumber);

    response.status(HttpStatus.CREATED_201);
    response.header(HttpHeader.LOCATION.asString(), bedPath);

    return bedPath;
  }

  public Object getAll(Request request, Response response) {
    Map<String, String[]> queryMap = buildQueryMap(request);

    List<BedResponse> bedResponses = bedService.getAll(queryMap);

    response.status(HttpStatus.OK_200);
    return bedResponses;
  }

  public Object getByNumber(Request request, Response response) {
    String bedNumber = request.params("number");
    BedResponse bedResponse = bedService.getByNumber(bedNumber);

    response.status(HttpStatus.OK_200);
    return bedResponse;
  }

  private HashMap<String, String[]> buildQueryMap(Request request) {
    HashMap<String, String[]> queryMap = new HashMap<>();

    addToQueryMap(request, BED_TYPE_PARAM, queryMap);
    addToQueryMap(request, CLEANING_FREQUENCY_PARAM, queryMap);
    addToQueryMap(request, MIN_CAPACITY_PARAM, queryMap);
    addToQueryMap(request, PACKAGE_NAME_PARAM, queryMap);

    if (request.queryParams(BLOOD_TYPES_PARAM) != null)
      queryMap.put(
          BLOOD_TYPES_PARAM, request.queryParams(BLOOD_TYPES_PARAM).replace(" ", "+").split(","));

    return queryMap;
  }

  private void addToQueryMap(Request request, String param, Map<String, String[]> queryMap) {
    if (request.queryParams(param) != null)
      queryMap.put(param, new String[] {request.queryParams(param)});
  }
}
