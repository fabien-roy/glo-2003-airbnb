package ca.ulaval.glo2003.bookings.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.bookings.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class BookingResourceTest {

  private BookingResource bookingResource;
  private BookingService bookingService;

  @BeforeEach
  public void setUpResource() {
    bookingService = mock(BookingService.class);
    bookingResource = new BookingResource(bookingService);
  }

  @Test
  public void add_shouldReturnCorrectHeaderLocation() throws JsonProcessingException {
    String expectedBedNumber = "expectedBedNumber";
    String expectedBookingNumber = "expectedBookingNumber";
    String expectedHeaderLocation =
        "/beds/" + expectedBedNumber + "/bookings/" + expectedBookingNumber;
    Request request = mock(Request.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    String requestBody = new ObjectMapper().writeValueAsString(bookingRequest);
    when(request.body()).thenReturn(requestBody);
    Response response = mock(Response.class);
    when(request.params(eq("bedNumber"))).thenReturn(expectedBedNumber);
    when(bookingService.add(any(), any())).thenReturn(expectedBookingNumber);

    bookingResource.add(request, response);

    verify(response).header(HttpHeader.LOCATION.asString(), expectedHeaderLocation);
  }

  @Test
  public void add_shouldSetCreatedAsHttpStatus() throws JsonProcessingException {
    Request request = mock(Request.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    String requestBody = new ObjectMapper().writeValueAsString(bookingRequest);
    when(request.body()).thenReturn(requestBody);
    Response response = mock(Response.class);

    bookingResource.add(request, response);

    verify(response).status(HttpStatus.CREATED_201);
  }

  @Test
  public void getByNumber_shouldReturnBooking() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);
    String bedNumber = "bedNumber";
    String bookingNumber = "bookingNumber";
    when(request.params(eq("bedNumber"))).thenReturn(bedNumber);
    when(request.params(eq("bookingNumber"))).thenReturn(bookingNumber);
    BookingResponse expectedBookingResponse = mock(BookingResponse.class);
    when(bookingService.getByNumber(bedNumber, bookingNumber)).thenReturn(expectedBookingResponse);

    BookingResponse bookingResponse =
        (BookingResponse) bookingResource.getByNumber(request, response);

    assertSame(expectedBookingResponse, bookingResponse);
  }

  @Test
  public void getByNumber_shouldSetOKAsHttpStatus() {
    Request request = mock(Request.class);
    Response response = mock(Response.class);

    bookingResource.getByNumber(request, response);

    verify(response).status(HttpStatus.OK_200);
  }
}
