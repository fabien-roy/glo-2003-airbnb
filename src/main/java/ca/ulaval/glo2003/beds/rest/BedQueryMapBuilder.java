package ca.ulaval.glo2003.beds.rest;

import java.util.HashMap;
import java.util.Map;
import spark.Request;

public class BedQueryMapBuilder {

  public static final String BED_TYPE_PARAM = "bedType";
  public static final String CLEANING_FREQUENCY_PARAM = "cleaningFreq";
  public static final String BLOOD_TYPES_PARAM = "bloodTypes";
  public static final String MIN_CAPACITY_PARAM = "minCapacity";
  public static final String PACKAGE_NAME_PARAM = "packages";
  public static final String ARRIVAL_DATE_PARAM = "arrivalDate";
  public static final String NUMBER_OF_NIGHTS_PARAM = "numberOfNights";
  public static final String LODGING_MODE_PARAM = "lodgingMode";
  public static final String ORIGIN_PARAM = "origin";
  public static final String MAX_DISTANCE_PARAM = "maxDistance";

  public Map<String, String[]> buildQueryMap(Request request) {
    Map<String, String[]> queryMap = new HashMap<>();

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
