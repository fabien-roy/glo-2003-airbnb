package ca.ulaval.glo2003.transactions;

import ca.ulaval.glo2003.parsers.domain.serializers.ThrowingSerializer;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.infrastructure.InMemoryTransactionRepository;
import ca.ulaval.glo2003.transactions.mappers.TransactionMapper;
import ca.ulaval.glo2003.transactions.rest.TransactionParser;
import ca.ulaval.glo2003.transactions.rest.TransactionResource;
import ca.ulaval.glo2003.transactions.rest.serializers.PriceSerializer;
import ca.ulaval.glo2003.transactions.rest.serializers.TransactionDeserializatingModule;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class TransactionModule extends AbstractModule {

  @Override
  protected void configure() {
    configureSerializers();

    bind(TransactionRepository.class).to(InMemoryTransactionRepository.class).in(Singleton.class);
    bind(TransactionParser.class);
    bind(TransactionService.class);
    bind(TransactionMapper.class);
    bind(TransactionResource.class);
  }

  private void configureSerializers() {
    Multibinder<ThrowingSerializer> serializerMultibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<ThrowingSerializer>() {});
    serializerMultibinder.addBinding().to(PriceSerializer.class);
    bind(TransactionDeserializatingModule.class);
  }
}
