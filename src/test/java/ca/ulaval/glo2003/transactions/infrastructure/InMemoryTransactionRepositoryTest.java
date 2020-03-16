package ca.ulaval.glo2003.transactions.infrastructure;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryTransactionRepositoryTest {

  private TransactionRepository transactionRepository;

  @BeforeEach
  public void setUpRepository() {
    transactionRepository = new InMemoryTransactionRepository();
  }

  @Test
  public void add_shouldAddTransaction() {
    Transaction expectedTransaction = aTransaction().build();

    transactionRepository.add(expectedTransaction);
    Transaction actualTransaction = transactionRepository.getAll().get(0);

    assertSame(expectedTransaction, actualTransaction);
  }

  @Test
  public void getAll_withOneTransaction_shouldGetOneTransaction() {
    Transaction expectedTransaction = aTransaction().build();
    transactionRepository.add(expectedTransaction);

    List<Transaction> actualTransactions = transactionRepository.getAll();

    assertEquals(1, actualTransactions.size());
    assertSame(expectedTransaction, actualTransactions.get(0));
  }

  @Test
  public void getAll_withMultipleTransactions_shouldGetMultipleTransactions() {
    Transaction expectedTransaction = aTransaction().build();
    Transaction otherExpectedTransaction = aTransaction().build();
    transactionRepository.add(expectedTransaction);
    transactionRepository.add(otherExpectedTransaction);

    List<Transaction> actualTransactions = transactionRepository.getAll();

    assertEquals(2, actualTransactions.size());
    assertTrue(actualTransactions.contains(expectedTransaction));
    assertTrue(actualTransactions.contains(otherExpectedTransaction));
  }
}
