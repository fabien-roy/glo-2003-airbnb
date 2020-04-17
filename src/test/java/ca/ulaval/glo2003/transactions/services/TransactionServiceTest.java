package ca.ulaval.glo2003.transactions.services;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.*;
import static ca.ulaval.glo2003.transactions.services.TransactionService.AIRBNB;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.transactions.converters.TransactionConverter;
import ca.ulaval.glo2003.transactions.domain.*;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TransactionServiceTest {

  private static TransactionService transactionService;
  private static TransactionFactory transactionFactory;
  private static TransactionRepository transactionRepository;
  private static TransactionConverter transactionConverter;

  private static Transaction transaction = aTransaction().build();
  private static Transaction otherTransaction = aTransaction().build();
  private static Transaction anotherTransaction = aTransaction().build();
  private static TransactionResponse transactionResponse = mock(TransactionResponse.class);
  private static TransactionResponse otherTransactionResponse = mock(TransactionResponse.class);
  private static String tenant = createFrom();
  private static String owner = createTo();
  private static Price tenantRefund = createTotal();
  private static Price ownerRefund = createTotal();
  private static Price total = createTotal();
  private static Timestamp departureDate = createTimestamp();

  @BeforeAll
  public static void setUpService() {
    transactionFactory = mock(TransactionFactory.class);
    transactionRepository = mock(TransactionRepository.class);
    transactionConverter = mock(TransactionConverter.class);
    transactionService =
        new TransactionService(transactionFactory, transactionRepository, transactionConverter);
  }

  private void resetMocks() {
    reset(transactionFactory, transactionRepository, transactionConverter);
  }

  private void setUpMocksForGetAll() {
    when(transactionRepository.getAll()).thenReturn(Arrays.asList(transaction, otherTransaction));
    when(transactionConverter.toResponse(transaction)).thenReturn(transactionResponse);
    when(transactionConverter.toResponse(otherTransaction)).thenReturn(otherTransactionResponse);
  }

  private void setUpMocksForAddStayBooked() {
    resetMocks();
    when(transactionFactory.createStayBooked(tenant, AIRBNB, total)).thenReturn(transaction);
  }

  private void setUpMocksForAddStayCompleted() {
    resetMocks();
    when(transactionFactory.createStayCompleted(AIRBNB, owner, total, departureDate))
        .thenReturn(transaction);
  }

  private void setUpMocksForAddStayCanceledHalfRefund() {
    resetMocks();
    when(transactionFactory.createStayCanceled(AIRBNB, tenant, tenantRefund))
        .thenReturn(transaction);
    when(transactionFactory.createStayCanceled(AIRBNB, owner, ownerRefund))
        .thenReturn(otherTransaction);
    when(transactionFactory.createStayRefunded(owner, AIRBNB, total, departureDate))
        .thenReturn(anotherTransaction);
  }

  private void setUpMocksForAddStayCanceledFullRefund() {
    resetMocks();
    when(transactionFactory.createStayCanceled(AIRBNB, tenant, total)).thenReturn(transaction);
    when(transactionFactory.createStayRefunded(owner, AIRBNB, total, departureDate))
        .thenReturn(otherTransaction);
  }

  @Test
  public void getAll_shouldGetAllTransactions() {
    setUpMocksForGetAll();

    List<TransactionResponse> transactionResponses = transactionService.getAllResponses();

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

    transactionService.addStayCompleted(owner, total, departureDate);

    verify(transactionRepository).add(eq(transaction));
  }

  @Test
  public void addStayCanceledHalfRefund_shouldAddTransactionCancelTenantToRepository() {
    setUpMocksForAddStayCanceledHalfRefund();

    transactionService.addStayCanceledHalfRefund(
        tenant, owner, tenantRefund, ownerRefund, total, departureDate);

    verify(transactionRepository).add(eq(transaction));
  }

  @Test
  public void addStayCanceledHalfRefund_shouldAddTransactionCancelOwnerToRepository() {
    setUpMocksForAddStayCanceledHalfRefund();

    transactionService.addStayCanceledHalfRefund(
        tenant, owner, tenantRefund, ownerRefund, total, departureDate);

    verify(transactionRepository).add(eq(otherTransaction));
  }

  @Test
  public void addStayCanceledHalfRefund_shouldAddTransactionRefundToRepository() {
    setUpMocksForAddStayCanceledHalfRefund();

    transactionService.addStayCanceledHalfRefund(
        tenant, owner, tenantRefund, ownerRefund, total, departureDate);

    verify(transactionRepository).add(eq(anotherTransaction));
  }

  @Test
  public void addStayCanceledFullRefund_shouldAddTransactionCancelToRepository() {
    setUpMocksForAddStayCanceledFullRefund();

    transactionService.addStayCanceledFullRefund(tenant, owner, total, departureDate);

    verify(transactionRepository).add(eq(transaction));
  }

  @Test
  public void addStayCanceledFullRefund_shouldAddTransactionRefundToRepository() {
    setUpMocksForAddStayCanceledFullRefund();

    transactionService.addStayCanceledFullRefund(tenant, owner, total, departureDate);

    verify(transactionRepository).add(eq(otherTransaction));
  }
}
