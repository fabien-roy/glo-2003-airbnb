package ca.ulaval.glo2003.beds.bookings.transactions.services;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers.TransactionMapper;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedMatcher;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionService {

  private final TransactionFactory transactionFactory;
  private final TransactionMapper transactionMapper;
  private final BedRepository bedRepository;
  private final BedMatcherMapper bedMatcherMapper;

  public TransactionService(
      TransactionFactory transactionFactory,
      TransactionMapper transactionMapper,
      BedRepository bedRepository,
      BedMatcherMapper bedMatcherMapper) {

    this.transactionFactory = transactionFactory;
    this.transactionMapper = transactionMapper;
    this.bedRepository = bedRepository;
    this.bedMatcherMapper = bedMatcherMapper;
  }

  public List<TransactionResponse> getAll(Map<String, String[]> params) {

    BedMatcher bedMatcher = bedMatcherMapper.fromRequestParams(params);

    List<Bed> beds = bedRepository.getAll();
    List<Bed> matchingBeds = beds.stream().filter(bedMatcher::matches).collect(Collectors.toList());
    List<Booking> bookings;
    List<Transaction> transactionsBooking;
    List<Transaction> transactionsBookingsAll = new ArrayList<>();

    for (int i = 0; i < matchingBeds.size(); i++) {
      bookings = matchingBeds.get(i).getBookings();

      for (int j = 0; j < bookings.size(); j++) {
        transactionsBooking = bookings.get(j).getTransactions();

        for (int t = 0; t < transactionsBooking.size(); t++) {

          transactionsBookingsAll.add(transactionsBooking.get(t));
        }
      }
    }

    return transactionsBookingsAll.stream()
        .map(transactionBooking -> transactionMapper.toResponse(transactionBooking))
        .collect(Collectors.toList());
  }
}
