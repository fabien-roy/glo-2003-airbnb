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
  private final BookingMapper bookingParser;

  @Inject
  public BookingResource(BookingService bookingService, BookingMapper bookingParser) {
    this.bookingService = bookingService;
    this.bookingParser = bookingParser;
  }

  @Override
  public void addRoutes() {
    post("", this::add);
    get("/:bookingNumber", this::getByNumber, bookingParser::writeValueAsString);
    post("/:bookingNumber/cancel", this::cancel, bookingParser::writeValueAsString);
  }

  public Object add(Request request, Response response) throws JsonProcessingException {
    BookingRequest bookingRequest = bookingParser.readValue(request.body(), BookingRequest.class);

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
