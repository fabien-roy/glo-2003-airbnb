package ca.ulaval.glo2003.transactions.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.transactions.rest.mappers.TransactionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {

  private final BedRepository bedRepository;
  private final TransactionMapper transactionMapper;

  public TransactionService(BedRepository bedRepository, TransactionMapper transactionMapper) {
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
}
