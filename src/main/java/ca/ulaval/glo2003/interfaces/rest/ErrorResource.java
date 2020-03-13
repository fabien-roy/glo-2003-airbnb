package ca.ulaval.glo2003.interfaces.rest;

import static spark.Spark.exception;

import ca.ulaval.glo2003.beds.bookings.rest.exceptions.BookingNotFoundException;
import ca.ulaval.glo2003.beds.bookings.rest.handlers.BookingNotFoundExceptionHandler;
import ca.ulaval.glo2003.beds.rest.exceptions.*;
import ca.ulaval.glo2003.beds.rest.handlers.*;
import ca.ulaval.glo2003.interfaces.clients.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.clients.handler.InvalidZipCodeExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo2003.interfaces.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.InvalidFormatExceptionHandler;
import ca.ulaval.glo2003.interfaces.rest.handlers.JsonProcessingExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import spark.RouteGroup;

public class ErrorResource implements RouteGroup {

  public static final String ERROR_PATH = "*";

  @Override
  public void addRoutes() {
    exception(Exception.class, new CatchallExceptionHandler());
    exception(JsonProcessingException.class, new JsonProcessingExceptionHandler());
    exception(InvalidFormatException.class, new InvalidFormatExceptionHandler());
    exception(InvalidBedTypeException.class, new InvalidBedTypeExceptionHandler());
    exception(BedNotFoundException.class, new BedNotFoundExceptionHandler());
    exception(
        InvalidCleaningFrequencyException.class, new InvalidCleaningFrequencyExceptionHandler());
    exception(InvalidBloodTypesException.class, new InvalidBloodTypesExceptionHandler());
    exception(
        ExceedingAccommodationCapacityException.class,
        new ExceedingAccommodationCapacityExceptionHandler());
    exception(InvalidCapacityException.class, new InvalidCapacityExceptionHandler());
    exception(InvalidZipCodeException.class, new InvalidZipCodeExceptionHandler());
    exception(InvalidPublicKeyException.class, new InvalidPublicKeyExceptionHandler());
    exception(InvalidPackageException.class, new InvalidPackageExceptionHandler());
    exception(BedAlreadyBookedException.class, new BedAlreadyBookedExceptionHandler());
    exception(BookingNotAllowedException.class, new BookingNotAllowedExceptionHandler());
    exception(PackageNotAvailableException.class, new PackageNotAvailableExceptionHandler());
    exception(BookingNotFoundException.class, new BookingNotFoundExceptionHandler());
    exception(
        AllYouCanDrinkDependencyException.class, new AllYouCanDrinkDependencyExceptionHandler());
    exception(SweetToothDependencyException.class, new SweetToothDependencyExceptionHandler());
  }
}
