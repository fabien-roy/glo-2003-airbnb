package ca.ulaval.glo2003.transactions.infrastructure;

import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {

  private List<Transaction> transactions;

  public InMemoryTransactionRepository() {
    transactions = new ArrayList<>();
  }

  @Override
  public void add(Transaction transaction) {
    transactions.add(transaction);
  }

  @Override
  public List<Transaction> getAll() {
    return transactions;
  }

  @Override
  public void deleteAll() {}
}
