package ca.ulaval.glo2003.transactions.services;

import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.mappers.TransactionMapper;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class TransactionService {

  public static final String AIRBNB = "airbnb";

  private final TransactionFactory transactionFactory;
  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;

  @Inject
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
    Transaction transaction = transactionFactory.createStayBooked(tenant, AIRBNB, total);
    transactionRepository.add(transaction);
  }

  public void addStayCompleted(String owner, Price total, int numberOfNights) {
    Transaction transaction =
        transactionFactory.createStayCompleted(AIRBNB, owner, total, numberOfNights);
    transactionRepository.add(transaction);
  }

  public void addStayCanceledHalfRefund(
      String tenant,
      String owner,
      Price tenantRefund,
      Price ownerRefund,
      Price total,
      int numberOfNights) {
    Transaction transactionCancelTenant =
        transactionFactory.createStayCanceled(AIRBNB, tenant, tenantRefund);
    Transaction transactionCancelOwner =
        transactionFactory.createStayCanceled(AIRBNB, owner, ownerRefund);
    Transaction transactionRefund =
        transactionFactory.createStayRefunded(owner, AIRBNB, total, numberOfNights);
    transactionRepository.add(transactionCancelTenant);
    transactionRepository.add(transactionCancelOwner);
    transactionRepository.add(transactionRefund);
  }

  public void addStayCanceledFullRefund(
      String tenant, String owner, Price total, int numberOfNights) {
    Transaction transactionCancel = transactionFactory.createStayCanceled(AIRBNB, tenant, total);
    Transaction transactionRefund =
        transactionFactory.createStayRefunded(owner, AIRBNB, total, numberOfNights);
    transactionRepository.add(transactionCancel);
    transactionRepository.add(transactionRefund);
  }
}
