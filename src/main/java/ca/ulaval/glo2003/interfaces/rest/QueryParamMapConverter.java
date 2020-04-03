package ca.ulaval.glo2003.interfaces.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryParamMapConverter {

  private static final String PARAM_DELIMITER = "&";
  private static final String KEY_DELIMITER = "=";
  private static final String VALUE_DELIMITER = ",";

  public Map<String, List<String>> fromString(String queryString) {
    Map<String, List<String>> map = new HashMap<>();

    if (queryString != null)
      parseQueryParams(queryString)
          .forEach(param -> map.put(parseQueryParamKey(param), parseQueryParamValues(param)));

    return map;
  }

  private List<String> parseQueryParams(String queryString) {
    return Arrays.asList(queryString.split(PARAM_DELIMITER));
  }

  private String parseQueryParamKey(String queryParam) {
    return queryParam.substring(0, queryParam.indexOf(KEY_DELIMITER));
  }

  private List<String> parseQueryParamValues(String queryParam) {
    return Arrays.asList(
        queryParam.substring(queryParam.indexOf(KEY_DELIMITER) + 1).split(VALUE_DELIMITER));
  }
}
