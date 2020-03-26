package ca.ulaval.glo2003.transactions;

import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.infrastructure.InMemoryTransactionRepository;
import ca.ulaval.glo2003.transactions.mappers.TransactionMapper;
import ca.ulaval.glo2003.transactions.rest.TransactionParser;
import ca.ulaval.glo2003.transactions.rest.TransactionResource;
import ca.ulaval.glo2003.transactions.rest.serializers.TransactionSerializingModule;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TransactionModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(TransactionRepository.class).to(InMemoryTransactionRepository.class).in(Singleton.class);
    bind(TransactionSerializingModule.class);
    bind(TransactionParser.class);
    bind(TransactionService.class);
    bind(TransactionMapper.class);
    bind(TransactionResource.class);
  }
}
