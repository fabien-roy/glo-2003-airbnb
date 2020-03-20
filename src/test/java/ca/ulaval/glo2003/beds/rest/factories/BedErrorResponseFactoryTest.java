package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.beds.rest.factories.BedErrorResponseFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorResponseFactory;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BedErrorResponseFactoryTest {

  private static BedErrorResponseFactory bedErrorResponseFactory;

  @BeforeAll
  public static void setUpFactory() {
    bedErrorResponseFactory = new BedErrorResponseFactory();
  }

  @ParameterizedTest
  @MethodSource("provideResponseForBedException")
  public void create_withBedException_shouldCreateAssociatedStatus(
      BedException exception, String expectedResponse) {
    String status = bedErrorResponseFactory.create(exception);

    assertEquals(expectedResponse, status);
  }

  @Test
  public void create_withException_shouldCreateDefaultStatus() {
    Exception exception = new Exception();
    String expectedResponse = new CatchallErrorResponseFactory().create(exception);

    String response = bedErrorResponseFactory.create(exception);

    assertEquals(expectedResponse, response);
  }

  private static Stream<Arguments> provideResponseForBedException() {
    String bedNumber = "bedNumber";

    return Stream.of(
        Arguments.of(new BedNotFoundException(bedNumber), bedNotFound(bedNumber)),
        Arguments.of(new InvalidBedTypeException(), invalidBedType()),
        Arguments.of(new InvalidBloodTypesException(), invalidBloodTypes()),
        Arguments.of(new InvalidCapacityException(), invalidCapacity()),
        Arguments.of(new InvalidCleaningFrequencyException(), invalidCleaningFrequency()),
        Arguments.of(new InvalidLodgingModeException(), invalidLodgingMode()),
        Arguments.of(new InvalidPackageException(), invalidPackage()),
        Arguments.of(new InvalidMaxDistanceException(), invalidMaxDistance()),
        Arguments.of(
            new ArrivalDateWithoutMinimalCapacityException(), arrivalDateWithoutMinimalCapacity()),
        Arguments.of(
            new NumberOfNightsWithoutMinimalCapacityException(),
            numberOfNightsWithoutMinimalCapacity()),
        Arguments.of(new MaxDistanceWithoutOriginException(), maxDistanceWithoutOrigin()),
        Arguments.of(new InvalidPublicKeyException(), invalidPublicKey()),
        Arguments.of(new PackageNotAvailableException(), packageNotAvailable()),
        Arguments.of(new AllYouCanDrinkDependencyException(), cantOfferAllYouCanDrinkPackage()),
        Arguments.of(new SweetToothDependencyException(), cantOfferSweetToothPackage()),
        Arguments.of(
            new ExceedingAccommodationCapacityException(), exceedingAccommodationCapacity()),
        Arguments.of(new BedAlreadyBookedException(), bedAlreadyBooked()),
        Arguments.of(new BookingNotAllowedException(), bookingNotAllowed()));
  }
}
