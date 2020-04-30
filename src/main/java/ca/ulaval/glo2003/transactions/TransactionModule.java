package ca.ulaval.glo2003.transactions;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.transactions.domain.TransactionRepository;
import ca.ulaval.glo2003.transactions.exceptions.TransactionException;
import ca.ulaval.glo2003.transactions.infrastructure.InMemoryTransactionRepository;
import ca.ulaval.glo2003.transactions.rest.TransactionMapper;
import ca.ulaval.glo2003.transactions.rest.TransactionResource;
import ca.ulaval.glo2003.transactions.rest.factories.InvalidServiceFeeErrorFactory;
import ca.ulaval.glo2003.transactions.rest.factories.OutOfBoundsServiceFeeErrorFactory;
import ca.ulaval.glo2003.transactions.rest.handlers.TransactionExceptionHandler;
import ca.ulaval.glo2003.transactions.rest.serializers.TransactionDeserializingModule;
import ca.ulaval.glo2003.transactions.rest.serializers.TransactionSerializingModule;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class TransactionModule extends AbstractModule {

  @Override
  protected void configure() {
    configureErrorFactories();

    bind(TransactionRepository.class).to(InMemoryTransactionRepository.class).in(Singleton.class);
    bind(TransactionSerializingModule.class);
    bind(TransactionDeserializingModule.class);
    bind(TransactionMapper.class);
    bind(TransactionService.class);
    bind(TransactionResource.class);
    bind(TransactionExceptionHandler.class);
  }

  private void configureErrorFactories() {
    Multibinder<ErrorFactory<TransactionException>> multibinder =
        Multibinder.newSetBinder(
            binder(), new TypeLiteral<ErrorFactory<TransactionException>>() {});
    multibinder.addBinding().to(InvalidServiceFeeErrorFactory.class);
    multibinder.addBinding().to(OutOfBoundsServiceFeeErrorFactory.class);
  }
}
