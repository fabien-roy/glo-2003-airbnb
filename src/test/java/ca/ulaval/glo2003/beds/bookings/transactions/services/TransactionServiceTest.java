package ca.ulaval.glo2003.beds.bookings.transactions.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers.TransactionMapper;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionServiceTest {

  private TransactionService transactionService;
  private BedRepository bedRepository;
  private TransactionMapper transactionMapper;

  @BeforeEach
  public void setUpService() {
    bedRepository = mock(BedRepository.class);
    transactionMapper = mock(TransactionMapper.class);
    transactionService = new TransactionService(bedRepository, transactionMapper);
  }

  @Test
  public void getAll_withASingleBedBookingAndTransaction_shouldGetAllTransactions() {
    TransactionResponse expectedResponse = mock(TransactionResponse.class);
    Bed bed = mock(Bed.class);
    Booking booking = mock(Booking.class);
    Transaction transaction = mock(Transaction.class);
    when(bedRepository.getAll()).thenReturn(Collections.singletonList(bed));
    when(bed.getBookings()).thenReturn(Collections.singletonList(booking));
    when(booking.getTransactions()).thenReturn(Collections.singletonList(transaction));
    when(transactionMapper.toResponse(transaction)).thenReturn(expectedResponse);

    List<TransactionResponse> responses = transactionService.getAll();

    assertEquals(1, responses.size());
    assertSame(expectedResponse, responses.get(0));
  }

  @Test
  public void getAll_withMultipleBeds_shouldGetAllTransactions() {
    TransactionResponse expectedResponse = mock(TransactionResponse.class);
    TransactionResponse otherExpectedResponse = mock(TransactionResponse.class);
    Bed bed = mock(Bed.class);
    Bed otherBed = mock(Bed.class);
    Booking booking = mock(Booking.class);
    Booking otherBooking = mock(Booking.class);
    Transaction transaction = mock(Transaction.class);
    Transaction otherTransaction = mock(Transaction.class);
    when(bedRepository.getAll()).thenReturn(Arrays.asList(bed, otherBed));
    when(bed.getBookings()).thenReturn(Collections.singletonList(booking));
    when(otherBed.getBookings()).thenReturn(Collections.singletonList(otherBooking));
    when(booking.getTransactions()).thenReturn(Collections.singletonList(transaction));
    when(otherBooking.getTransactions()).thenReturn(Collections.singletonList(otherTransaction));
    when(transactionMapper.toResponse(transaction)).thenReturn(expectedResponse);
    when(transactionMapper.toResponse(otherTransaction)).thenReturn(otherExpectedResponse);

    List<TransactionResponse> responses = transactionService.getAll();

    assertEquals(2, responses.size());
    assertTrue(responses.containsAll(Arrays.asList(expectedResponse, otherExpectedResponse)));
  }

  @Test
  public void getAll_withMultipleBookings_shouldGetAllTransactions() {
    TransactionResponse expectedResponse = mock(TransactionResponse.class);
    TransactionResponse otherExpectedResponse = mock(TransactionResponse.class);
    Bed bed = mock(Bed.class);
    Booking booking = mock(Booking.class);
    Booking otherBooking = mock(Booking.class);
    Transaction transaction = mock(Transaction.class);
    Transaction otherTransaction = mock(Transaction.class);
    when(bedRepository.getAll()).thenReturn(Collections.singletonList(bed));
    when(bed.getBookings()).thenReturn(Arrays.asList(booking, otherBooking));
    when(booking.getTransactions()).thenReturn(Collections.singletonList(transaction));
    when(otherBooking.getTransactions()).thenReturn(Collections.singletonList(otherTransaction));
    when(transactionMapper.toResponse(transaction)).thenReturn(expectedResponse);
    when(transactionMapper.toResponse(otherTransaction)).thenReturn(otherExpectedResponse);

    List<TransactionResponse> responses = transactionService.getAll();

    assertEquals(2, responses.size());
    assertTrue(responses.containsAll(Arrays.asList(expectedResponse, otherExpectedResponse)));
  }

  @Test
  public void getAll_withMultipleTransactions_shouldGetAllTransactions() {
    TransactionResponse expectedResponse = mock(TransactionResponse.class);
    TransactionResponse otherExpectedResponse = mock(TransactionResponse.class);
    Bed bed = mock(Bed.class);
    Booking booking = mock(Booking.class);
    Transaction transaction = mock(Transaction.class);
    Transaction otherTransaction = mock(Transaction.class);
    when(bedRepository.getAll()).thenReturn(Collections.singletonList(bed));
    when(bed.getBookings()).thenReturn(Collections.singletonList(booking));
    when(booking.getTransactions()).thenReturn(Arrays.asList(transaction, otherTransaction));
    when(transactionMapper.toResponse(transaction)).thenReturn(expectedResponse);
    when(transactionMapper.toResponse(otherTransaction)).thenReturn(otherExpectedResponse);

    List<TransactionResponse> responses = transactionService.getAll();

    assertEquals(2, responses.size());
    assertTrue(responses.containsAll(Arrays.asList(expectedResponse, otherExpectedResponse)));
  }
}
