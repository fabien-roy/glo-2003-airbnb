package ca.ulaval.glo2003.transactions.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.transactions.rest.mappers.TransactionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {

  private final TransactionFactory transactionFactory;
  private final TransactionRepository transactionRepository;
  private final BedRepository bedRepository;
  private final TransactionMapper transactionMapper;

  public TransactionService(
      TransactionFactory transactionFactory,
      TransactionRepository transactionRepository,
      BedRepository bedRepository,
      TransactionMapper transactionMapper) {
    this.transactionFactory = transactionFactory;
    this.transactionRepository = transactionRepository;
    this.bedRepository = bedRepository;
    this.transactionMapper = transactionMapper;
  }

  public List<TransactionResponse> getAll() {
    List<Bed> beds = bedRepository.getAll();

    List<Transaction> transactions = new ArrayList<>();
    beds.forEach(
        bed ->
            bed.getBookings().forEach(booking -> transactions.addAll(booking.getTransactions())));

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
