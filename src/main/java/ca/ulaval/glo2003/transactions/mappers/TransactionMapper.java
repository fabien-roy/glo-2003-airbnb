package ca.ulaval.glo2003.transactions.mappers;

import ca.ulaval.glo2003.beds.mappers.PriceMapper;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import javax.inject.Inject;

public class TransactionMapper {

  private final TimestampMapper timestampMapper;
  private final PriceMapper priceMapper;

  @Inject
  public TransactionMapper(TimestampMapper timestampMapper, PriceMapper priceMapper) {
    this.timestampMapper = timestampMapper;
    this.priceMapper = priceMapper;
  }

  public TransactionResponse toResponse(Transaction transaction) {
    String timestamp = timestampMapper.toString(transaction.getTimestamp());
    Double total = priceMapper.toDouble(transaction.getTotal());
    return new TransactionResponse(
        timestamp,
        transaction.getFrom(),
        transaction.getTo(),
        total,
        transaction.getReason().toString());
  }
}
