package ca.ulaval.glo2003.beds.rest;

import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.services.BedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

public class BedResourceTest {

  private BedResource bedResource;
  private BedService bedService;

  @BeforeEach
  public void setUpResource() {
    bedService = mock(BedService.class);
    bedResource = new BedResource(bedService);
  }

  @Test
  public void add_shouldBedNumberAsHeaderLocation() throws JsonProcessingException {
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
  public void get_shouldReturnBed() {
    // TODO
  }

  @Test
  public void get_shouldSetOKAsHttpStatus() {
    // TODO
  }

  @Test
  public void getAll_shouldReturnAllBeds() {
    // TODO
  }

  @Test
  public void getAll_shouldSetOKAsHttpStatus() {
    // TODO
  }
}
