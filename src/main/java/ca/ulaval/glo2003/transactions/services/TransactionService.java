package ca.ulaval.glo2003.transactions.services;

import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.transactions.rest.mappers.TransactionMapper;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {

  private final TransactionFactory transactionFactory;
  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;

  public TransactionService(
      TransactionFactory transactionFactory,
      TransactionRepository transactionRepository,
      TransactionMapper transactionMapper) {
    this.transactionFactory = transactionFactory;
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
  }

  public List<TransactionResponse> getAll() {
    List<Transaction> transactions = transactionRepository.getAll();

    return transactions.stream().map(transactionMapper::toResponse).collect(Collectors.toList());
  }

  public void addStayBooked(String tenant, Price total) {
    Transaction transactionBooked = transactionFactory.createStayBooked(tenant, total);
    transactionRepository.add(transactionBooked);
  }

  public void addStayCompleted(String owner, Price total, int numberOfNights) {
    Transaction transactionBooked =
        transactionFactory.createStayCompleted(owner, total, numberOfNights);
    transactionRepository.add(transactionBooked);
  }
}
