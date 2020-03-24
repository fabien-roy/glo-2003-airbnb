package ca.ulaval.glo2003.transactions.services;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.*;
import static ca.ulaval.glo2003.transactions.rest.helpers.TransactionResponseBuilder.aTransactionResponse;
import static ca.ulaval.glo2003.transactions.services.TransactionService.AIRBNB;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.mappers.TransactionMapper;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionServiceTest {

  private static TransactionService transactionService;
  private static TransactionFactory transactionFactory;
  private static TransactionRepository transactionRepository;
  private static TransactionMapper transactionMapper;

  private Transaction transaction = aTransaction().build();
  private Transaction otherTransaction = aTransaction().build();
  private TransactionResponse transactionResponse = aTransactionResponse().build();
  private TransactionResponse otherTransactionResponse = aTransactionResponse().build();
  private String tenant = createFrom();
  private String owner = createTo();
  private Price total = createTotal();
  private int numberOfNights = 3;

  @BeforeAll
  public static void setUpService() {
    transactionFactory = mock(TransactionFactory.class);
    transactionRepository = mock(TransactionRepository.class);
    transactionMapper = mock(TransactionMapper.class);
    transactionService =
        new TransactionService(transactionFactory, transactionRepository, transactionMapper);
  }

  @BeforeEach
  public void setUpMocksForGetAll() {
    when(transactionRepository.getAll()).thenReturn(Arrays.asList(transaction, otherTransaction));
    when(transactionMapper.toResponse(transaction)).thenReturn(transactionResponse);
    when(transactionMapper.toResponse(otherTransaction)).thenReturn(otherTransactionResponse);
  }

  @BeforeEach
  public void setUpMocksForAdd() {
    when(transactionFactory.createStayBooked(tenant, AIRBNB, total)).thenReturn(transaction);
    when(transactionFactory.createStayCompleted(AIRBNB, owner, total, numberOfNights))
        .thenReturn(transaction);
  }

  @Test
  public void getAll_shouldGetAllTransactions() {
    List<TransactionResponse> transactionResponses = transactionService.getAll();

    assertTrue(transactionResponses.contains(transactionResponse));
    assertTrue(transactionResponses.contains(otherTransactionResponse));
  }

  @Test
  public void addStayBooked_shouldAddTransactionToRepository() {
    transactionService.addStayBooked(tenant, total);

    verify(transactionRepository).add(eq(transaction));
  }

  @Test
  public void addStayCompleted_shouldAddTransactionToRepository() {
    transactionService.addStayCompleted(owner, total, numberOfNights);

    verify(transactionRepository).add(eq(transaction));
  }
}
