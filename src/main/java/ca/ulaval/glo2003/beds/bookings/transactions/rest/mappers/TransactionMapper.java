package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.beds.rest.mappers.PriceMapper;
import java.time.format.DateTimeFormatter;

public class TransactionMapper {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
  private PriceMapper priceMapper;

  public TransactionMapper(PriceMapper priceMapper) {
    this.priceMapper = priceMapper;
  }

  public TransactionResponse toResponse(Transaction transaction) {
    String timestamp = transaction.getTimestamp().format(DATE_TIME_FORMATTER) + "Z";
    Double total = priceMapper.toDouble(transaction.getTotal());
    return new TransactionResponse(
        timestamp,
        transaction.getFrom(),
        transaction.getTo(),
        total,
        transaction.getReason().toString());
  }
}
