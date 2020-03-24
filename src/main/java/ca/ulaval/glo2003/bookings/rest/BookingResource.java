package ca.ulaval.glo2003.bookings.rest;

import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static spark.Spark.*;

import ca.ulaval.glo2003.bookings.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class BookingResource implements RouteGroup {

  public static final String SINGLE_BOOKING_PATH = "/bookings";
  public static final String BOOKING_PATH = "/beds/:bedNumber/bookings";

  private final BookingService bookingService;

  @Inject
  public BookingResource(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @Override
  public void addRoutes() {
    post("", this::add);
    get("/:bookingNumber", this::getByNumber, new ObjectMapper()::writeValueAsString);
    post("/:bookingNumber/cancel", this::cancel, new ObjectMapper()::writeValueAsString);
  }

  public Object add(Request request, Response response) throws JsonProcessingException {
    BookingRequest bookingRequest;

    bookingRequest = new ObjectMapper().readValue(request.body(), BookingRequest.class);

    String bedNumber = request.params("bedNumber");
    String bookingNumber = bookingService.add(bedNumber, bookingRequest);
    String bookingPath =
        String.format("%s/%s%s/%s", BED_PATH, bedNumber, SINGLE_BOOKING_PATH, bookingNumber);

    response.status(HttpStatus.CREATED_201);
    response.header(HttpHeader.LOCATION.asString(), bookingPath);

    return bookingPath;
  }

  public Object getByNumber(Request request, Response response) {
    String bedNumber = request.params("bedNumber");
    String bookingNumber = request.params("bookingNumber");
    BookingResponse bookingResponse = bookingService.getByNumber(bedNumber, bookingNumber);

    response.status(HttpStatus.OK_200);
    return bookingResponse;
  }

  public Object cancel(Request request, Response response) {
    String bedNumber = request.params("bedNumber");
    String bookingNumber = request.params("bookingNumber");
    CancelResponse cancelResponse = bookingService.cancel(bedNumber, bookingNumber);

    response.status(HttpStatus.OK_200);
    return cancelResponse;
  }
}
