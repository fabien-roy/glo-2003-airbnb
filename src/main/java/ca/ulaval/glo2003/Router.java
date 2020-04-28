package ca.ulaval.glo2003;

import static ca.ulaval.glo2003.admin.rest.AdminResource.ADMIN_PATH;
import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static ca.ulaval.glo2003.bookings.rest.BookingResource.BOOKING_PATH;
import static ca.ulaval.glo2003.errors.rest.ErrorMapper.ERROR_PATH;
import static ca.ulaval.glo2003.transactions.rest.TransactionResource.TRANSACTION_PATH;
import static spark.Spark.path;

import ca.ulaval.glo2003.admin.rest.AdminResource;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.bookings.rest.BookingResource;
import ca.ulaval.glo2003.errors.rest.ErrorMapper;
import ca.ulaval.glo2003.transactions.rest.TransactionResource;
import com.google.inject.Inject;

public class Router {

  private final ErrorMapper errorMapper;
  private final TransactionResource transactionResource;
  private final BedResource bedResource;
  private final BookingResource bookingResource;
  private final AdminResource adminResource;

  @Inject
  public Router(
      ErrorMapper errorMapper,
      TransactionResource transactionResource,
      BedResource bedResource,
      BookingResource bookingResource,
      AdminResource adminResource) {
    this.errorMapper = errorMapper;
    this.transactionResource = transactionResource;
    this.bedResource = bedResource;
    this.bookingResource = bookingResource;
    this.adminResource = adminResource;
  }

  public void setUpRoutes() {
    path(ERROR_PATH, errorMapper);
    path(TRANSACTION_PATH, transactionResource);
    path(BED_PATH, bedResource);
    path(BOOKING_PATH, bookingResource);
    path(ADMIN_PATH, adminResource);
  }
}
