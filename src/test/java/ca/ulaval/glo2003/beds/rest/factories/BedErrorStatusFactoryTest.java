package ca.ulaval.glo2003.beds.rest.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorStatusFactory;
import java.util.stream.Stream;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BedErrorStatusFactoryTest {

  private static BedErrorStatusFactory bedErrorStatusFactory;

  @BeforeAll
  public static void setUpFactory() {
    bedErrorStatusFactory = new BedErrorStatusFactory();
  }

  @ParameterizedTest
  @MethodSource("provideStatusForBedException")
  public void create_withBedException_shouldCreateAssociatedStatus(
      BedException exception, int expectedStatus) {
    int status = bedErrorStatusFactory.create(exception);

    assertEquals(expectedStatus, status);
  }

  @Test
  public void create_withException_shouldCreateDefaultStatus() {
    Exception exception = new Exception();
    int expectedStatus = new CatchallErrorStatusFactory().create(exception);

    int status = bedErrorStatusFactory.create(exception);

    assertEquals(expectedStatus, status);
  }

  private static Stream<Arguments> provideStatusForBedException() {
    return Stream.of(
        Arguments.of(new BedNotFoundException("bedNumber"), HttpStatus.NOT_FOUND_404),
        Arguments.of(new InvalidBedTypeException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidBloodTypesException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidCapacityException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidCleaningFrequencyException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidLodgingModeException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidPackageException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidMaxDistanceException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new MaxDistanceWithoutOriginException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new InvalidPublicKeyException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new PackageNotAvailableException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new AllYouCanDrinkDependencyException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new SweetToothDependencyException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new ExceedingAccommodationCapacityException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new BedAlreadyBookedException(), HttpStatus.BAD_REQUEST_400),
        Arguments.of(new BookingNotAllowedException(), HttpStatus.BAD_REQUEST_400));
  }
}
