package ca.ulaval.glo2003;

import static ca.ulaval.glo2003.beds.bookings.rest.BookingResource.BOOKING_PATH;
import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static ca.ulaval.glo2003.interfaces.rest.mappers.ErrorMapper.ERROR_PATH;
import static ca.ulaval.glo2003.transactions.rest.TransactionResource.TRANSACTION_PATH;
import static spark.Spark.path;

import ca.ulaval.glo2003.beds.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.beds.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.beds.bookings.rest.BookingResource;
import ca.ulaval.glo2003.beds.bookings.rest.factories.BookingErrorResponseFactory;
import ca.ulaval.glo2003.beds.bookings.rest.factories.BookingErrorStatusFactory;
import ca.ulaval.glo2003.beds.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingDateMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.beds.bookings.services.BookingService;
import ca.ulaval.glo2003.beds.domain.BedFactory;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.BedStarsCalculator;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedRepository;
import ca.ulaval.glo2003.beds.rest.BedQueryMapBuilder;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.beds.rest.factories.BedErrorResponseFactory;
import ca.ulaval.glo2003.beds.rest.factories.BedErrorStatusFactory;
import ca.ulaval.glo2003.beds.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.beds.rest.mappers.*;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.interfaces.infrastructure.ZippopotamusClient;
import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorResponseFactory;
import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorStatusFactory;
import ca.ulaval.glo2003.interfaces.rest.factories.ExternalServiceErrorResponseFactory;
import ca.ulaval.glo2003.interfaces.rest.factories.ExternalServiceErrorStatusFactory;
import ca.ulaval.glo2003.interfaces.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.ExternalServiceExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.mappers.ErrorMapper;
import ca.ulaval.glo2003.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.infrastructure.InMemoryTransactionRepository;
import ca.ulaval.glo2003.transactions.rest.TransactionResource;
import ca.ulaval.glo2003.transactions.rest.mappers.TransactionMapper;
import ca.ulaval.glo2003.transactions.services.TransactionService;

public class Router {

  private static final TransactionRepository transactionRepository =
      new InMemoryTransactionRepository();
  private static final BedRepository bedRepository = new InMemoryBedRepository();

  private static final ZippopotamusClient zippopotamusClient = new ZippopotamusClient();

  private static final BedFactory bedFactory = new BedFactory();
  private static final BookingFactory bookingFactory = new BookingFactory();
  private static final TransactionFactory transactionFactory = new TransactionFactory();

  private static final BedStarsCalculator bedStarsCalculator = new BedStarsCalculator();
  private static final BookingTotalCalculator bookingTotalCalculator = new BookingTotalCalculator();

  private static final PriceMapper priceMapper = new PriceMapper();
  private static final PublicKeyMapper publicKeyMapper = new PublicKeyMapper();
  private static final PackageMapper packageMapper = new PackageMapper(priceMapper);
  private static final BookingDateMapper bookingDateMapper = new BookingDateMapper();
  private static final BookingMapper bookingMapper =
      new BookingMapper(publicKeyMapper, bookingDateMapper, priceMapper);
  private static final BookingNumberMapper bookingNumberMapper = new BookingNumberMapper();
  private static final BedMapper bedMapper = new BedMapper(publicKeyMapper, packageMapper);
  private static final BedNumberMapper bedNumberMapper = new BedNumberMapper();
  private static final BedMatcherMapper bedMatcherMapper = new BedMatcherMapper(bookingDateMapper);
  private static final TransactionMapper transactionMapper = new TransactionMapper(priceMapper);

  private static final CatchallErrorStatusFactory catchallErrorStatusFactory =
      new CatchallErrorStatusFactory();
  private static final CatchallErrorResponseFactory catchallErrorResponseFactory =
      new CatchallErrorResponseFactory();
  private static final ExternalServiceErrorStatusFactory externalServiceErrorStatusFactory =
      new ExternalServiceErrorStatusFactory();
  private static final ExternalServiceErrorResponseFactory externalServiceErrorResponseFactory =
      new ExternalServiceErrorResponseFactory();
  private static final BedErrorStatusFactory bedErrorStatusFactory = new BedErrorStatusFactory();
  private static final BedErrorResponseFactory bedErrorResponseFactory =
      new BedErrorResponseFactory();
  private static final BookingErrorStatusFactory bookingErrorStatusFactory =
      new BookingErrorStatusFactory();
  private static final BookingErrorResponseFactory bookingErrorResponseFactory =
      new BookingErrorResponseFactory();

  private static final CatchallExceptionHandler catchallExceptionHandler =
      new CatchallExceptionHandler(catchallErrorStatusFactory, catchallErrorResponseFactory);
  private static final ExternalServiceExceptionHandler externalServiceExceptionHandler =
      new ExternalServiceExceptionHandler(
          externalServiceErrorStatusFactory, externalServiceErrorResponseFactory);
  private static final BedExceptionHandler bedExceptionHandler =
      new BedExceptionHandler(bedErrorStatusFactory, bedErrorResponseFactory);
  private static final BookingExceptionHandler bookingExceptionHandler =
      new BookingExceptionHandler(bookingErrorStatusFactory, bookingErrorResponseFactory);

  private static final TransactionService transactionService =
      new TransactionService(transactionFactory, transactionRepository, transactionMapper);
  private static final BedService bedService =
      new BedService(
          bedFactory,
          bedMapper,
          bedNumberMapper,
          bedMatcherMapper,
          bedRepository,
          bedStarsCalculator,
          zippopotamusClient);
  private static final BookingService bookingService =
      new BookingService(
          transactionService,
          bookingMapper,
          bedRepository,
          bookingFactory,
          bookingTotalCalculator,
          bedNumberMapper,
          bookingNumberMapper);

  private static final BedQueryMapBuilder bedQueryMapBuilder = new BedQueryMapBuilder();

  public static void setUpRoutes() {
    path(
        ERROR_PATH,
        new ErrorMapper(
            catchallExceptionHandler,
            externalServiceExceptionHandler,
            bedExceptionHandler,
            bookingExceptionHandler));
    path(BED_PATH, new BedResource(bedService, bedQueryMapBuilder));
    path(BOOKING_PATH, new BookingResource(bookingService));
    path(TRANSACTION_PATH, new TransactionResource(transactionService));
  }
}
