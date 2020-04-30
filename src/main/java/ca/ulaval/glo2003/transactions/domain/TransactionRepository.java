package ca.ulaval.glo2003.transactions.domain;

import java.util.List;

public interface TransactionRepository {

  void add(Transaction transaction);

  List<Transaction> getAll();

  void deleteAll();
}
