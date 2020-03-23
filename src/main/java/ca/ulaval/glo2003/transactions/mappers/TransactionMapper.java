package ca.ulaval.glo2003.transactions.mappers;

import ca.ulaval.glo2003.beds.mappers.PriceMapper;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;

public class TransactionMapper {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  private final PriceMapper priceMapper;

  @Inject
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
