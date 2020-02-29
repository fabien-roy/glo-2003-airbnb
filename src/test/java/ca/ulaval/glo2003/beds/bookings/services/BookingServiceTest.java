package ca.ulaval.glo2003.beds.bookings.services;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingResponse;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedFactory;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.BedStarsCalculator;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import ca.ulaval.glo2003.beds.services.BedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    private BookingService bookingService;
    private TransactionFactory transactionFactory;
    private BedRepository bedRepository;
    private BookingMapper bookingMapper;
    private BedNumberMapper bedNumberMapper;
    private BookingNumberMapper bookingNumberMapper;

    @BeforeEach
    public void setUpService() {
        transactionFactory = mock(TransactionFactory.class);
        bedRepository = mock(BedRepository.class);
        bookingMapper = mock(BookingMapper.class);
        bookingNumberMapper = mock(BookingNumberMapper.class);
        bedNumberMapper = mock(BedNumberMapper.class);
        bookingService = new BookingService(transactionFactory, bedRepository, bookingMapper, bedNumberMapper, bookingNumberMapper);
    }

    @Test
    public void getByNumber_withNumber_shouldGetBooking() {
        String requestedBedNumber = "requestedBedNumber";
        String requestedBookingNumber = "requestedBookingNumber";
        UUID bedNumber = mock(UUID.class);
        UUID bookingNumber = mock(UUID.class);
        when(bedNumberMapper.fromString(requestedBedNumber)).thenReturn(bedNumber);
        when(bookingNumberMapper.fromString(requestedBookingNumber)).thenReturn(bookingNumber);
        Bed bed = mock(Bed.class);
        when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
        Booking booking = mock(Booking.class);
        when(bed.getBookingByNumber(bookingNumber)).thenReturn(booking);
        BookingResponse expectedBookingResponse = mock(BookingResponse.class);
        when(bookingMapper.toResponse(booking)).thenReturn(expectedBookingResponse);

        BookingResponse bookingResponse = bookingService.getByNumber(requestedBedNumber, requestedBookingNumber);

        assertEquals(expectedBookingResponse, bookingResponse);
    }


}