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
import org.junit.jupiter.api.Test;

class TransactionServiceTest {

  private static TransactionService transactionService;
  private static TransactionFactory transactionFactory = mock(TransactionFactory.class);
  private static TransactionRepository transactionRepository = mock(TransactionRepository.class);
  private static TransactionMapper transactionMapper = mock(TransactionMapper.class);

  private static Transaction transaction = aTransaction().build();
  private static Transaction otherTransaction = aTransaction().build();
  private static Transaction anotherTransaction = aTransaction().build();
  private static TransactionResponse transactionResponse = aTransactionResponse().build();
  private static TransactionResponse otherTransactionResponse = aTransactionResponse().build();
  private static String tenant = createFrom();
  private static String owner = createTo();
  private static Price tenantTotal = createTotal();
  private static Price ownerTotal = createTotal();
  private static Price total = createTotal();
  private static int numberOfNights = 3;

  @BeforeAll
  public static void setUpService() {
    transactionService =
        new TransactionService(transactionFactory, transactionRepository, transactionMapper);
  }

  private void resetMocks() {
    reset(transactionFactory, transactionRepository, transactionMapper);
  }

  private void setUpMocksForGetAll() {
    when(transactionRepository.getAll()).thenReturn(Arrays.asList(transaction, otherTransaction));
    when(transactionMapper.toResponse(transaction)).thenReturn(transactionResponse);
    when(transactionMapper.toResponse(otherTransaction)).thenReturn(otherTransactionResponse);
  }

  private void setUpMocksForAddStayBooked() {
    resetMocks();
    when(transactionFactory.createStayBooked(tenant, AIRBNB, total)).thenReturn(transaction);
  }

  private void setUpMocksForAddStayCompleted() {
    resetMocks();
    when(transactionFactory.createStayCompleted(AIRBNB, owner, total, numberOfNights))
        .thenReturn(transaction);
  }

  private void setUpMocksForAddStayCanceledHalfRefund() {
    resetMocks();
    when(transactionFactory.createStayCanceled(AIRBNB, tenant, tenantTotal))
        .thenReturn(transaction);
    when(transactionFactory.createStayCanceled(AIRBNB, owner, ownerTotal))
        .thenReturn(otherTransaction);
    when(transactionFactory.createStayRefunded(owner, AIRBNB, total, numberOfNights))
        .thenReturn(anotherTransaction);
  }

  private void setUpMocksForAddStayCanceledFullRefund() {
    resetMocks();
    when(transactionFactory.createStayCanceled(AIRBNB, tenant, total)).thenReturn(transaction);
    when(transactionFactory.createStayRefunded(owner, AIRBNB, total, numberOfNights))
        .thenReturn(otherTransaction);
  }

  @Test
  public void getAll_shouldGetAllTransactions() {
    setUpMocksForGetAll();

    List<TransactionResponse> transactionResponses = transactionService.getAll();

    assertTrue(transactionResponses.contains(transactionResponse));
    assertTrue(transactionResponses.contains(otherTransactionResponse));
  }

  @Test
  public void addStayBooked_shouldAddTransactionToRepository() {
    setUpMocksForAddStayBooked();

    transactionService.addStayBooked(tenant, total);

    verify(transactionRepository).add(eq(transaction));
  }

  @Test
  public void addStayCompleted_shouldAddTransactionToRepository() {
    setUpMocksForAddStayCompleted();

    transactionService.addStayCompleted(owner, total, numberOfNights);

    verify(transactionRepository).add(eq(transaction));
  }

  @Test
  public void addStayCanceledHalfRefund_shouldAddTransactionCancelTenantToRepository() {
    setUpMocksForAddStayCanceledHalfRefund();

    transactionService.addStayCanceledHalfRefund(
        tenant, owner, tenantTotal, ownerTotal, total, numberOfNights);

    verify(transactionRepository).add(eq(transaction));
  }

  @Test
  public void addStayCanceledHalfRefund_shouldAddTransactionCancelOwnerToRepository() {
    setUpMocksForAddStayCanceledHalfRefund();

    transactionService.addStayCanceledHalfRefund(
        tenant, owner, tenantTotal, ownerTotal, total, numberOfNights);

    verify(transactionRepository).add(eq(otherTransaction));
  }

  @Test
  public void addStayCanceledHalfRefund_shouldAddTransactionRefundToRepository() {
    setUpMocksForAddStayCanceledHalfRefund();

    transactionService.addStayCanceledHalfRefund(
        tenant, owner, tenantTotal, ownerTotal, total, numberOfNights);

    verify(transactionRepository).add(eq(anotherTransaction));
  }

  @Test
  public void addStayCanceledFullRefund_shouldAddTransactionCancelToRepository() {
    setUpMocksForAddStayCanceledFullRefund();

    transactionService.addStayCanceledFullRefund(tenant, owner, total, numberOfNights);

    verify(transactionRepository).add(eq(transaction));
  }

  @Test
  public void addStayCanceledFullRefund_shouldAddTransactionRefundToRepository() {
    setUpMocksForAddStayCanceledFullRefund();

    transactionService.addStayCanceledFullRefund(tenant, owner, total, numberOfNights);

    verify(transactionRepository).add(eq(otherTransaction));
  }
}
