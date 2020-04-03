package ca.ulaval.glo2003.interfaces.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QueryParamMapConverterTest {

  private static QueryParamMapConverter queryParamMapConverter;

  private static String firstParam = "firstParam";
  private static String firstValue = "firstValue";
  private static String secondParam = "secondParam";
  private static String secondValueA = "secondValueA";
  private static String secondValueB = "secondValueB";
  private static String singleQueryParam = firstParam + "=" + firstValue;
  private static String multipleQueryParams =
      firstParam + "=" + firstValue + "&" + secondParam + "=" + secondValueA + "," + secondValueB;

  private static Map<String, List<String>> queryParamMap;

  @BeforeAll
  public static void setUpConverter() {
    queryParamMapConverter = new QueryParamMapConverter();
  }

  @Test
  public void fromString_shouldConvertQueryParams() {
    queryParamMap = queryParamMapConverter.fromString(singleQueryParam);

    assertEquals(1, queryParamMap.size());
    assertEquals(1, queryParamMap.get(firstParam).size());
    assertEquals(firstValue, queryParamMap.get(firstParam).get(0));
  }

  @Test
  public void fromString_withoutQueryParam_shouldConvertEmptyMap() {
    queryParamMap = queryParamMapConverter.fromString(null);

    assertEquals(0, queryParamMap.size());
  }

  @Test
  public void fromString_withMultipleParams_shouldConvertQueryParams() {
    queryParamMap = queryParamMapConverter.fromString(multipleQueryParams);

    assertEquals(2, queryParamMap.size());
  }

  @Test
  public void fromString_withMultipleParamValues_shouldConvertQueryParamValues() {
    queryParamMap = queryParamMapConverter.fromString(multipleQueryParams);

    assertEquals(2, queryParamMap.get(secondParam).size());
    assertTrue(queryParamMap.get(secondParam).contains(secondValueA));
    assertTrue(queryParamMap.get(secondParam).contains(secondValueB));
  }
}
