package ca.ulaval.glo2003.transactions.services;

import ca.ulaval.glo2003.transactions.converters.TransactionConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class TransactionService {

  public static final String AIRBNB = "airbnb";

  private final TransactionFactory transactionFactory;
  private final TransactionRepository transactionRepository;
  private final TransactionConverter transactionConverter;

  @Inject
  public TransactionService(
      TransactionFactory transactionFactory,
      TransactionRepository transactionRepository,
      TransactionConverter transactionConverter) {
    this.transactionFactory = transactionFactory;
    this.transactionRepository = transactionRepository;
    this.transactionConverter = transactionConverter;
  }

  public List<TransactionResponse> getAll() {
    List<Transaction> transactions = transactionRepository.getAll();

    return transactions.stream().map(transactionConverter::toResponse).collect(Collectors.toList());
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
