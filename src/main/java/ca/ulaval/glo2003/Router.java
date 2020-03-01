package ca.ulaval.glo2003;

import static ca.ulaval.glo2003.beds.bookings.rest.BookingResource.BOOKING_PATH;
import static ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResource.TRANSACTION_PATH;
import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static ca.ulaval.glo2003.interfaces.rest.ErrorResource.ERROR_PATH;
import static ca.ulaval.glo2003.interfaces.rest.RootResource.ROOT_PATH;
import static spark.Spark.path;

import ca.ulaval.glo2003.beds.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.beds.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.beds.bookings.rest.BookingResource;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.beds.bookings.services.BookingService;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResource;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers.TransactionMapper;
import ca.ulaval.glo2003.beds.bookings.transactions.services.TransactionService;
import ca.ulaval.glo2003.beds.domain.BedFactory;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.BedStarsCalculator;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedRepository;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import ca.ulaval.glo2003.beds.rest.mappers.PackageMapper;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.interfaces.rest.ErrorResource;
import ca.ulaval.glo2003.interfaces.rest.RootResource;

public class Router {

  // TODO : This is not correct dependency injection
  private static final BedRepository bedRepository = new InMemoryBedRepository();

  private static final BedFactory bedFactory = new BedFactory();
  private static final BookingFactory bookingFactory = new BookingFactory();
  private static final TransactionFactory transactionFactory = new TransactionFactory();

  private static final BedStarsCalculator bedStarsCalculator = new BedStarsCalculator();
  private static final BookingTotalCalculator bookingTotalCalculator = new BookingTotalCalculator();

  private static final PackageMapper packageMapper = new PackageMapper();
  private static final BedMapper bedMapper = new BedMapper(packageMapper);
  private static final BedNumberMapper bedNumberMapper = new BedNumberMapper();
  private static final BedMatcherMapper bedMatcherMapper = new BedMatcherMapper();
  private static final TransactionMapper transactionMapper = new TransactionMapper();
  private static final BookingMapper bookingMapper = new BookingMapper();
  private static final BookingNumberMapper bookingNumberMapper = new BookingNumberMapper();

  private static final BedService bedService =
      new BedService(
          bedFactory,
          bedMapper,
          bedNumberMapper,
          bedMatcherMapper,
          bedRepository,
          bedStarsCalculator);
  private static final BookingService bookingService =
      new BookingService(
          transactionFactory,
          bookingMapper,
          bedRepository,
          bookingFactory,
          bookingTotalCalculator,
          bedNumberMapper,
          bookingNumberMapper);
  private static final TransactionService transactionService =
      new TransactionService(bedRepository, transactionMapper);

  public static void setUpRoutes() {
    path(ERROR_PATH, new ErrorResource());
    path(ROOT_PATH, new RootResource());
    path(BED_PATH, new BedResource(bedService));
    path(BOOKING_PATH, new BookingResource(bookingService));
    path(TRANSACTION_PATH, new TransactionResource(transactionService));
  }
}
