package ca.ulaval.glo2003.beds.bookings.rest;

import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static spark.Spark.get;
import static spark.Spark.post;

import ca.ulaval.glo2003.beds.bookings.services.BookingService;
import ca.ulaval.glo2003.interfaces.rest.handlers.JsonProcessingExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class BookingResource implements RouteGroup {

  public static final String SINGLE_BOOKING_PATH = "/bookings";
  public static final String BOOKING_PATH = "/beds/:bedNumber/bookings";

  private final BookingService bookingService;

  public BookingResource(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @Override
  public void addRoutes() {
    post("", this::add);
    get("/:bookingNumber", this::getByNumber, new ObjectMapper()::writeValueAsString);
  }

  public Object add(Request request, Response response) {
    BookingRequest bookingRequest;

    try {
      bookingRequest = new ObjectMapper().readValue(request.body(), BookingRequest.class);

    } catch (JsonProcessingException exception) {
      new JsonProcessingExceptionHandler().handle(exception, request, response);

      return response.body();
    }

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
}
