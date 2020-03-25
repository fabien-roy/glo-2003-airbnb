package ca.ulaval.glo2003.bookings.rest;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.bookings.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class BookingResourceTest {

  private static BookingResource bookingResource;
  private static BookingService bookingService = mock(BookingService.class);
  private static BookingParser bookingParser = mock(BookingParser.class);

  private Request request = mock(Request.class);
  private Response response = mock(Response.class);
  private BookingRequest bookingRequest = mock(BookingRequest.class);
  private BookingResponse bookingResponse = mock(BookingResponse.class);
  private CancelResponse cancelResponse = mock(CancelResponse.class);
  private String bedNumber = "bedNumber";
  private String bookingNumber = "bookingNumber";

  @BeforeAll
  public static void setUpResource() {
    bookingResource = new BookingResource(bookingService, bookingParser);
  }

  private void setUpMocksForAdd() throws JsonProcessingException {
    String requestBody = new ObjectMapper().writeValueAsString(bookingRequest);
    when(request.body()).thenReturn(requestBody);
    when(request.params(eq("bedNumber"))).thenReturn(bedNumber);
    when(bookingService.add(any(), any())).thenReturn(bookingNumber);
  }

  private void setUpMocksForGetByNumber() {
    when(request.params(eq("bedNumber"))).thenReturn(bedNumber);
    when(request.params(eq("bookingNumber"))).thenReturn(bookingNumber);
    when(bookingService.getByNumber(bedNumber, bookingNumber)).thenReturn(bookingResponse);
  }

  private void setUpMocksForCancel() {
    when(request.params(eq("bedNumber"))).thenReturn(bedNumber);
    when(request.params(eq("bookingNumber"))).thenReturn(bookingNumber);
    when(bookingService.cancel(bedNumber, bookingNumber)).thenReturn(cancelResponse);
  }

  @Test
  public void add_shouldReturnCorrectHeaderLocation() throws JsonProcessingException {
    setUpMocksForAdd();
    String headerLocation = "/beds/" + bedNumber + "/bookings/" + bookingNumber;

    bookingResource.add(request, response);

    verify(response).header(HttpHeader.LOCATION.asString(), headerLocation);
  }

  @Test
  public void add_shouldSetCreatedAsHttpStatus() throws JsonProcessingException {
    setUpMocksForAdd();

    bookingResource.add(request, response);

    verify(response).status(HttpStatus.CREATED_201);
  }

  @Test
  public void getByNumber_shouldReturnBooking() {
    setUpMocksForGetByNumber();

    BookingResponse actualResponse =
        (BookingResponse) bookingResource.getByNumber(request, response);

    assertSame(bookingResponse, actualResponse);
  }

  @Test
  public void getByNumber_shouldSetOKAsHttpStatus() {
    setUpMocksForGetByNumber();

    bookingResource.getByNumber(request, response);

    verify(response).status(HttpStatus.OK_200);
  }

  @Test
  public void cancel_shouldReturnCancelResponse() {
    setUpMocksForCancel();

    CancelResponse actualCancelResponse =
        (CancelResponse) bookingResource.cancel(request, response);

    assertSame(cancelResponse, actualCancelResponse);
  }

  @Test
  public void cancel_shouldSetOkAsHttpStatus() {
    setUpMocksForCancel();

    bookingResource.cancel(request, response);

    verify(response).status(HttpStatus.OK_200);
  }
}
