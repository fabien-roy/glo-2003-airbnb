package ca.ulaval.glo2003;

import static ca.ulaval.glo2003.admin.rest.AdminResource.ADMIN_PATH;
import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static ca.ulaval.glo2003.bookings.rest.BookingResource.BOOKING_PATH;
import static ca.ulaval.glo2003.errors.rest.ErrorMapper.ERROR_PATH;
import static ca.ulaval.glo2003.reports.rest.ReportResource.REPORT_PATH;
import static ca.ulaval.glo2003.transactions.rest.TransactionResource.TRANSACTION_PATH;
import static spark.Spark.path;

import ca.ulaval.glo2003.admin.rest.AdminResource;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.bookings.rest.BookingResource;
import ca.ulaval.glo2003.errors.rest.ErrorMapper;
import ca.ulaval.glo2003.reports.rest.ReportResource;
import ca.ulaval.glo2003.transactions.rest.TransactionResource;
import com.google.inject.Inject;

public class Router {

  private final ErrorMapper errorMapper;
  private final AdminResource adminResource;
  private final TransactionResource transactionResource;
  private final ReportResource reportResource;
  private final BedResource bedResource;
  private final BookingResource bookingResource;

  @Inject
  public Router(
      ErrorMapper errorMapper,
      AdminResource adminResource,
      TransactionResource transactionResource,
      ReportResource reportResource,
      BedResource bedResource,
      BookingResource bookingResource) {
    this.errorMapper = errorMapper;
    this.adminResource = adminResource;
    this.transactionResource = transactionResource;
    this.reportResource = reportResource;
    this.bedResource = bedResource;
    this.bookingResource = bookingResource;
  }

  public void setUpRoutes() {
    path(ERROR_PATH, errorMapper);
    path(ADMIN_PATH, adminResource);
    path(TRANSACTION_PATH, transactionResource);
    path(REPORT_PATH, reportResource);
    path(BED_PATH, bedResource);
    path(BOOKING_PATH, bookingResource);
  }
}
