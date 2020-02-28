package ca.ulaval.glo2003.beds.bookings.transactions.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers.TransactionMapper;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
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
  public void getAll_shouldGetAllTransactions() {
    TransactionResponse expectedResponse = mock(TransactionResponse.class);
    Bed bed = mock(Bed.class);
    List<Bed> beds = Collections.singletonList(bed);
    Booking booking = mock(Booking.class);
    List<Booking> bookings = Collections.singletonList(booking);
    Transaction transaction = mock(Transaction.class);
    List<Transaction> transactions = Collections.singletonList(transaction);
    when(bedRepository.getAll()).thenReturn(beds);
    when(bed.getBookings()).thenReturn(bookings);
    when(booking.getTransactions()).thenReturn(transactions);
    when(transactionMapper.toResponse(transaction)).thenReturn(expectedResponse);

    List<TransactionResponse> responses = transactionService.getAll();

    assertEquals(1, responses.size());
    assertSame(expectedResponse, responses.get(0));
  }
}
