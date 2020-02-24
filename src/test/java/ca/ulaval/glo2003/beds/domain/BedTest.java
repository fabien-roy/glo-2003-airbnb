package ca.ulaval.glo2003.beds.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BedTest {

  @Test
  void book_withSingleBooking_shouldAddBooking() {
    // TODO
  }

  @Test
  void book_withSameTenantAsBedOwner_shouldThrowBookingNotAllowedException() {
    // TODO
  }

  @Test
  void book_withAlreadyBookedDate_shouldThrowAlreadyBookedException() {
    // TODO
  }

  @Test
  void book_withUnavailablePackage_shouldThrowPackageUnavailableException() {
    // TODO
  }
}
