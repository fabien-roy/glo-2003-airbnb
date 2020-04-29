package ca.ulaval.glo2003.transactions.services;

import ca.ulaval.glo2003.admin.domain.Configuration;
import ca.ulaval.glo2003.time.domain.Timestamp;
import ca.ulaval.glo2003.transactions.converters.ServiceFeeConverter;
import ca.ulaval.glo2003.transactions.converters.TransactionConverter;
import ca.ulaval.glo2003.transactions.domain.*;
import ca.ulaval.glo2003.transactions.domain.ServiceFee;
import ca.ulaval.glo2003.transactions.rest.ServiceFeeRequest;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class TransactionService {

  public static final String AIRBNB = "airbnb";

  private final TransactionFactory transactionFactory;
  private final TransactionRepository transactionRepository;
  private final TransactionConverter transactionConverter;
  private final ServiceFeeConverter serviceFeeConverter;

  @Inject
  public TransactionService(
      TransactionFactory transactionFactory,
      TransactionRepository transactionRepository,
      TransactionConverter transactionConverter,
      ServiceFeeConverter serviceFeeConverter) {
    this.transactionFactory = transactionFactory;
    this.transactionRepository = transactionRepository;
    this.transactionConverter = transactionConverter;
    this.serviceFeeConverter = serviceFeeConverter;
  }

  public List<Transaction> getAll() {
    return transactionRepository.getAll();
  }

  public List<TransactionResponse> getAllResponses() {
    return getAll().stream().map(transactionConverter::toResponse).collect(Collectors.toList());
  }

  public void addStayBooked(String tenant, Price total) {
    Transaction transaction = transactionFactory.createStayBooked(tenant, AIRBNB, total);
    transactionRepository.add(transaction);
  }

  public void addStayCompleted(String owner, Price total, Timestamp departureDate) {
    Transaction transaction =
        transactionFactory.createStayCompleted(AIRBNB, owner, total, departureDate);
    transactionRepository.add(transaction);
  }

  public void addStayCanceledHalfRefund(
      String tenant,
      String owner,
      Price tenantRefund,
      Price ownerRefund,
      Price total,
      Timestamp departureDate) {
    Transaction transactionCancelTenant =
        transactionFactory.createStayCanceled(AIRBNB, tenant, tenantRefund);
    Transaction transactionCancelOwner =
        transactionFactory.createStayCanceled(AIRBNB, owner, ownerRefund);
    Transaction transactionRefund =
        transactionFactory.createStayRefunded(owner, AIRBNB, total, departureDate);
    transactionRepository.add(transactionCancelTenant);
    transactionRepository.add(transactionCancelOwner);
    transactionRepository.add(transactionRefund);
  }

  public void addStayCanceledFullRefund(
      String tenant, String owner, Price total, Timestamp departureDate) {
    Transaction transactionCancel = transactionFactory.createStayCanceled(AIRBNB, tenant, total);
    Transaction transactionRefund =
        transactionFactory.createStayRefunded(owner, AIRBNB, total, departureDate);
    transactionRepository.add(transactionCancel);
    transactionRepository.add(transactionRefund);
  }

  public void deleteAll() {
    transactionRepository.deleteAll();
  }

  // TODO : Test TransactionService.updateServiceFee
  public void updateServiceFee(ServiceFeeRequest request) {
    ServiceFee serviceFee = serviceFeeConverter.fromRequest(request);
    Configuration.instance().setServiceFee(serviceFee);
  }
}
