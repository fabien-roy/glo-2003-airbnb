package ca.ulaval.glo2003.bookings.rest;

import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static spark.Spark.get;
import static spark.Spark.post;

import ca.ulaval.glo2003.bookings.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
  private final BookingMapper bookingMapper;

  @Inject
  public BookingResource(BookingService bookingService, BookingMapper bookingMapper) {
    this.bookingService = bookingService;
    this.bookingMapper = bookingMapper;
  }

  @Override
  public void addRoutes() {
    post("", this::add);
    get("/:bookingNumber", this::getByNumber, bookingMapper::writeValueAsString);
    post("/:bookingNumber/cancel", this::cancel, bookingMapper::writeValueAsString);
  }

  public Object add(Request request, Response response) throws JsonProcessingException {
    BookingRequest bookingRequest = bookingMapper.readValue(request.body(), BookingRequest.class);

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
    BookingResponse bookingResponse = bookingService.getResponse(bedNumber, bookingNumber);

    response.status(HttpStatus.OK_200);
    return bookingResponse;
  }

  public Object cancel(Request request, Response response) {
    String bedNumber = request.params("bedNumber");
    String bookingNumber = request.params("bookingNumber");
    CancelationResponse cancelationResponse = bookingService.cancel(bedNumber, bookingNumber);

    response.status(HttpStatus.OK_200);
    return cancelationResponse;
  }
}
