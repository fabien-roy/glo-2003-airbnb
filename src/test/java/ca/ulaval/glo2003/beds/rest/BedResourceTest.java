package ca.ulaval.glo2003.beds.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.interfaces.rest.QueryParamMapConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

public class BedResourceTest {

  private static BedResource bedResource;
  private static BedService bedService = mock(BedService.class);
  private static BedMapper bedMapper = mock(BedMapper.class);
  private static QueryParamMapConverter queryParamMapConverter = mock(QueryParamMapConverter.class);

  private static String bedNumber = "bedNumber";

  private static Request request = mock(Request.class);
  private static Response response = mock(Response.class);
  private static BedRequest bedRequest = mock(BedRequest.class);
  private static BedResponse bedResponse = mock(BedResponse.class);
  private static BedResponse otherBedResponse = mock(BedResponse.class);
  private static Map<String, List<String>> queryParamsMap = mock(Map.class);
  private static HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

  @BeforeAll
  public static void setUpResource() {
    bedResource = new BedResource(bedService, bedMapper, queryParamMapConverter);
  }

  @BeforeEach
  public void setUpMocks() throws JsonProcessingException {
    reset(response);
    String requestBody = "requestBody";
    String queryString = "queryString";
    when(request.body()).thenReturn(requestBody);
    when(request.params(eq("number"))).thenReturn(bedNumber);
    when(request.raw()).thenReturn(httpServletRequest);
    when(httpServletRequest.getQueryString()).thenReturn(queryString);
    when(queryParamMapConverter.fromString(queryString)).thenReturn(queryParamsMap);
    when(bedService.getAllResponses(queryParamsMap))
        .thenReturn(Arrays.asList(bedResponse, otherBedResponse));
    when(bedService.add(bedRequest)).thenReturn(bedNumber);
    when(bedService.getResponse(bedNumber)).thenReturn(bedResponse);
    when(bedMapper.readValue(requestBody, BedRequest.class)).thenReturn(bedRequest);
  }

  @Test
  public void add_shouldReturnCorrectHeaderLocation() throws IOException {
    String headerLocation = "/beds/" + bedNumber;

    bedResource.add(request, response);

    verify(response).header(HttpHeader.LOCATION.asString(), headerLocation);
  }

  @Test
  public void add_shouldSetCreatedAsHttpStatus() throws IOException {
    bedResource.add(request, response);

    verify(response).status(HttpStatus.CREATED_201);
  }

  @Test
  public void getByNumber_shouldReturnBed() {
    BedResponse actualBedResponse = (BedResponse) bedResource.getByNumber(request, response);

    assertSame(bedResponse, actualBedResponse);
  }

  @Test
  public void getByNumber_shouldSetOKAsHttpStatus() {
    bedResource.getByNumber(request, response);

    verify(response).status(HttpStatus.OK_200);
  }

  @Test
  public void getAll_shouldReturnBeds() {
    List<BedResponse> bedResponses = (List<BedResponse>) bedResource.getAll(request, response);

    assertEquals(2, bedResponses.size());
    assertTrue(bedResponses.contains(bedResponse));
    assertTrue(bedResponses.contains(bedResponse));
  }

  @Test
  public void getAll_shouldSetOKAsHttpStatus() {
    bedResource.getAll(request, response);

    verify(response).status(HttpStatus.OK_200);
  }
}
