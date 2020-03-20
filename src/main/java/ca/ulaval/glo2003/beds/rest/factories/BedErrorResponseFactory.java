package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.*;
import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorResponseFactory;

public class BedErrorResponseFactory extends CatchallErrorResponseFactory {

  @Override
  public String create(Exception exception) {
    if (exception instanceof BedNotFoundException) {
      return bedNotFound(((BedNotFoundException) exception).getBedNumber());
    } else if (exception instanceof InvalidBedTypeException) {
      return invalidBedType();
    } else if (exception instanceof InvalidBloodTypesException) {
      return invalidBloodTypes();
    } else if (exception instanceof InvalidCapacityException) {
      return invalidCapacity();
    } else if (exception instanceof InvalidCleaningFrequencyException) {
      return invalidCleaningFrequency();
    } else if (exception instanceof InvalidLodgingModeException) {
      return invalidLodgingMode();
    } else if (exception instanceof InvalidPackageException) {
      return invalidPackage();
    } else if (exception instanceof InvalidMaxDistanceException) {
      return invalidMaxDistance();
    } else if (exception instanceof ArrivalDateWithoutMinimalCapacityException) {
      return arrivalDateWithoutMinimalCapacity();
    } else if (exception instanceof NumberOfNightsWithoutMinimalCapacityException) {
      return numberOfNightsWithoutMinimalCapacity();
    } else if (exception instanceof MaxDistanceWithoutOriginException) {
      return maxDistanceWithoutOrigin();
    } else if (exception instanceof InvalidPublicKeyException) {
      return invalidPublicKey();
    } else if (exception instanceof PackageNotAvailableException) {
      return packageNotAvailable();
    } else if (exception instanceof AllYouCanDrinkDependencyException) {
      return cantOfferAllYouCanDrinkPackage();
    } else if (exception instanceof SweetToothDependencyException) {
      return cantOfferSweetToothPackage();
    } else if (exception instanceof ExceedingAccommodationCapacityException) {
      return exceedingAccommodationCapacity();
    } else if (exception instanceof BedAlreadyBookedException) {
      return bedAlreadyBooked();
    } else if (exception instanceof BookingNotAllowedException) {
      return bookingNotAllowed();
    } else {
      return defaultResponse();
    }
  }

  static String bedNotFound(String number) {
    return tryWriteValueAsString(
        "BED_NOT_FOUND", "bed with number " + number + " could not be found");
  }

  static String invalidBedType() {
    return tryWriteValueAsString(
        "INVALID_BED_TYPE", "bed type should be one of latex, memoryFoam or springs");
  }

  static String invalidBloodTypes() {
    return tryWriteValueAsString(
        "INVALID_BLOOD_TYPES",
        "blood types should be one or many of O-, O+, AB-, AB+, B-, B+, A- or A+");
  }

  static String invalidCapacity() {
    return tryWriteValueAsString(
        "INVALID_CAPACITY", "minimal capacity should be a positive number");
  }

  static String invalidCleaningFrequency() {
    return tryWriteValueAsString(
        "INVALID_CLEANING_FREQUENCY",
        "cleaning frequency should be one of weekly, monthly, annual or never");
  }

  static String invalidLodgingMode() {
    return tryWriteValueAsString(
        "INVALID_LODGING_MODE", "lodging mode should be one of private or cohabitation");
  }

  static String invalidPackage() {
    return tryWriteValueAsString(
        "INVALID_PACKAGE", "package should be one of bloodthirsty, allYouCanDrink, sweetTooth");
  }

  static String invalidMaxDistance() {
    return tryWriteValueAsString(
        "INVALID_MAX_DISTANCE", "distance should be a number greater than 0");
  }

  static String arrivalDateWithoutMinimalCapacity() {
    return tryWriteValueAsString(
        "ARRIVAL_DATE_WITHOUT_MINIMAL_CAPACITY",
        "a minimal capacity should be provided along with the arrival date");
  }

  static String numberOfNightsWithoutMinimalCapacity() {
    return tryWriteValueAsString(
        "NUMBER_OF_NIGHTS_WITHOUT_MINIMAL_CAPACITY",
        "a minimal capacity should be provided along with the number of nights");
  }

  static String maxDistanceWithoutOrigin() {
    return tryWriteValueAsString(
        "MAX_DISTANCE_WITHOUT_ORIGIN",
        "an origin point should be provided along with the maximum distance");
  }

  static String invalidPublicKey() {
    return tryWriteValueAsString(
        "INVALID_PUBLIC_KEY",
        "BiteCoins account public key should contain only alphanumeric characters and have a 256-bits length");
  }

  static String packageNotAvailable() {
    return tryWriteValueAsString(
        "PACKAGE_NOT_AVAILABLE", "selected package is not available for this bed");
  }

  static String cantOfferAllYouCanDrinkPackage() {
    return tryWriteValueAsString(
        "CANT_OFFER_ALL_YOU_CAN_DRINK_PACKAGE",
        "in order to offer allYouCanDrink package, you must also offer the bloodthirsty package");
  }

  static String cantOfferSweetToothPackage() {
    return tryWriteValueAsString(
        "CANT_OFFER_SWEET_TOOTH_PACKAGE",
        "in order to offer sweetTooth package, you must also offer the bloodthirsty and allYouCanDrink packages");
  }

  static String exceedingAccommodationCapacity() {
    return tryWriteValueAsString(
        "EXCEEDING_ACCOMMODATION_CAPACITY",
        "accommodation capacity exceeding maximum viable capacity for the provided bed type");
  }

  static String bedAlreadyBooked() {
    return tryWriteValueAsString("BED_ALREADY_BOOKED", "bed is already booked for selected dates");
  }

  static String bookingNotAllowed() {
    return tryWriteValueAsString("BOOKING_NOT_ALLOWED", "bed owner cannot book its own bed");
  }
}
