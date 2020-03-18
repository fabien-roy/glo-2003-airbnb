package ca.ulaval.glo2003.beds.rest;

import static ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper.*;

import java.util.HashMap;
import java.util.Map;
import spark.Request;

public class BedQueryMapBuilder {

  public HashMap<String, String[]> buildQueryMap(Request request) {
    HashMap<String, String[]> queryMap = new HashMap<>();

    addToQueryMap(request, BED_TYPE_PARAM, queryMap);
    addToQueryMap(request, CLEANING_FREQUENCY_PARAM, queryMap);
    addToQueryMap(request, MIN_CAPACITY_PARAM, queryMap);
    addToQueryMap(request, PACKAGE_NAME_PARAM, queryMap);
    addToQueryMap(request, ORIGIN_PARAM, queryMap);
    addToQueryMap(request, MAX_DISTANCE_PARAM, queryMap);
    addToQueryMap(request, ARRIVAL_DATE_PARAM, queryMap);
    addToQueryMap(request, NUMBER_OF_NIGHTS_PARAM, queryMap);
    addToQueryMap(request, LODGING_MODE_PARAM, queryMap);

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
