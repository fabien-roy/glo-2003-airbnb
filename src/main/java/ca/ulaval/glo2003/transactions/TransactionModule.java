package ca.ulaval.glo2003.transactions;

import ca.ulaval.glo2003.parsers.rest.SerializingModule;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.infrastructure.InMemoryTransactionRepository;
import ca.ulaval.glo2003.transactions.mappers.TransactionMapper;
import ca.ulaval.glo2003.transactions.rest.TransactionParser;
import ca.ulaval.glo2003.transactions.rest.TransactionResource;
import ca.ulaval.glo2003.transactions.rest.serializers.PriceSerializer;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.util.Collections;

public class TransactionModule extends AbstractModule {

  @Override
  protected void configure() {
    configureSerializers();

    bind(TransactionRepository.class).to(InMemoryTransactionRepository.class).in(Singleton.class);
    bind(TransactionService.class);
    bind(TransactionMapper.class);
    bind(TransactionResource.class);
  }

  private void configureSerializers() {
    SerializingModule serializingModule =
        new SerializingModule(
            Collections.singletonList(new PriceSerializer()), Collections.emptyList());
    TransactionParser transactionParser =
        new TransactionParser(Collections.singletonList(serializingModule));

    bind(TransactionParser.class).toInstance(transactionParser);
  }
}
