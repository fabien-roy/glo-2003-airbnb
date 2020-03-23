package ca.ulaval.glo2003.beds.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.services.BedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

public class BedResourceTest {

  // TODO : Refactor this test class

  private BedResource bedResource;
  private BedService bedService;

  @BeforeEach
  public void setUpResource() {
    bedService = mock(BedService.class);
    bedResource = new BedResource(bedService);
  }

  @Test
  public void add_shouldReturnCorrectHeaderLocation() throws JsonProcessingException {
    String expectedBedNumber = "expectedBedNumber";
    String expectedHeaderLocation = "/beds/" + expectedBedNumber;
    Request request = mock(Request.class);
    BedRequest bedRequest = mock(BedRequest.class);
    String requestBody = new ObjectMapper().writeValueAsString(bedRequest);
    when(request.body()).thenReturn(requestBody);
    Response response = mock(Response.class);
    when(bedService.add(any())).thenReturn(expectedBedNumber);

    bedResource.add(request, response);

    verify(response).header(HttpHeader.LOCATION.asString(), expectedHeaderLocation);
  }

  @Test
  public void add_shouldSetCreatedAsHttpStatus() throws JsonProcessingException {
    Request request = mock(Request.class);
    BedRequest bedRequest = mock(BedRequest.class);
    String requestBody = new ObjectMapper().writeValueAsString(bedRequest);
    when(request.body()).thenReturn(requestBody);
    Response response = mock(Response.class);

    bedResource.add(request, response);

    verify(response).status(HttpStatus.CREATED_201);
  }

  @Test
  public void getByNumber_shouldReturnBed() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    String bedNumber = "bedNumber";
    when(request.params(eq("number"))).thenReturn(bedNumber);
    BedResponse expectedBedResponse = mock(BedResponse.class);
    when(bedService.getByNumber(bedNumber)).thenReturn(expectedBedResponse);

    BedResponse bedResponse = (BedResponse) bedResource.getByNumber(request, response);

    assertSame(expectedBedResponse, bedResponse);
  }

  @Test
  public void getByNumber_shouldSetOKAsHttpStatus() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);

    bedResource.getByNumber(request, response);

    verify(response).status(HttpStatus.OK_200);
  }

  @Test
  public void getAll_withOneBed_shouldReturnThatBed() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    BedResponse expectedBedResponse = mock(BedResponse.class);
    when(request.queryMap()).thenReturn(mock(QueryParamsMap.class));
    when(bedService.getAll(anyMap())).thenReturn(Collections.singletonList(expectedBedResponse));

    List<BedResponse> bedResponses = (List<BedResponse>) bedResource.getAll(request, response);

    assertEquals(1, bedResponses.size());
    assertTrue(bedResponses.contains(expectedBedResponse));
  }

  @Test
  public void getAll_withMultipleBeds_shouldReturnAllBeds() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    BedResponse expectedBedResponse = mock(BedResponse.class);
    BedResponse otherExpectedBedResponse = mock(BedResponse.class);
    when(request.queryMap()).thenReturn(mock(QueryParamsMap.class));
    when(bedService.getAll(anyMap()))
        .thenReturn(Arrays.asList(expectedBedResponse, otherExpectedBedResponse));

    List<BedResponse> bedResponses = (List<BedResponse>) bedResource.getAll(request, response);

    assertEquals(2, bedResponses.size());
    assertTrue(bedResponses.contains(expectedBedResponse));
    assertTrue(bedResponses.contains(otherExpectedBedResponse));
  }

  @Test
  public void getAll_shouldSetOKAsHttpStatus() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    when(request.queryMap()).thenReturn(mock(QueryParamsMap.class));

    bedResource.getAll(request, response);

    verify(response).status(HttpStatus.OK_200);
  }
}
