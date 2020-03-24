package ca.ulaval.glo2003.transactions.services;

import static ca.ulaval.glo2003.transactions.domain.TransactionFactory.AIRBNB;

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

  // TODO : This class could clearly be simplified

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
    Transaction transactionBooked = transactionFactory.createStayBooked(tenant, total);
    transactionRepository.add(transactionBooked);
  }

  public void addStayCompleted(String owner, Price total, int numberOfNights) {
    Transaction transactionBooked =
        transactionFactory.createStayCompleted(owner, total, numberOfNights);
    transactionRepository.add(transactionBooked);
  }

  // TODO : Test
  public void addStayCanceledHalfRefund(
      String tenant,
      String owner,
      Price tenantTotal,
      Price ownerTotal,
      Price total,
      int numberOfNights) {
    Transaction transactionCancelTenant =
        transactionFactory.createCancel(AIRBNB, tenant, tenantTotal);
    Transaction transactionCancelOwner = transactionFactory.createCancel(AIRBNB, owner, ownerTotal);
    Transaction transactionRefund =
        transactionFactory.createRefund(owner, AIRBNB, total, numberOfNights);
    transactionRepository.add(transactionCancelTenant);
    transactionRepository.add(transactionCancelOwner);
    transactionRepository.add(transactionRefund);
  }

  // TODO : Test
  public void addStayCanceledFullRefund(
      String tenant, String owner, Price total, int numberOfNights) {
    Transaction transactionCancel = transactionFactory.createCancel(AIRBNB, tenant, total);
    Transaction transactionRefund =
        transactionFactory.createRefund(owner, AIRBNB, total, numberOfNights);
    transactionRepository.add(transactionCancel);
    transactionRepository.add(transactionRefund);
  }
}
