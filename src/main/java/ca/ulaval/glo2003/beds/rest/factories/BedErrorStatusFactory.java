package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.errors.rest.factories.CatchallErrorStatusFactory;
import org.eclipse.jetty.http.HttpStatus;

public class BedErrorStatusFactory extends CatchallErrorStatusFactory {

  @Override
  public int create(Exception exception) {
    if (exception instanceof BedNotFoundException) {
      return HttpStatus.NOT_FOUND_404;
    } else if (exception instanceof InvalidBedTypeException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidBloodTypesException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidCapacityException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidCleaningFrequencyException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidLodgingModeException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidPackageException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidMaxDistanceException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof ArrivalDateWithoutMinimalCapacityException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof NumberOfNightsWithoutMinimalCapacityException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof MaxDistanceWithoutOriginException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidPublicKeyException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof PackageNotAvailableException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof AllYouCanDrinkDependencyException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof SweetToothDependencyException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof ExceedingAccommodationCapacityException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof BedAlreadyBookedException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof BookingNotAllowedException) {
      return HttpStatus.BAD_REQUEST_400;
    } else {
      return defaultStatus();
    }
  }
}
