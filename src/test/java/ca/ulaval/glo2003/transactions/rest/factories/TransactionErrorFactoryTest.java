package ca.ulaval.glo2003.transactions.rest.factories;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.errors.rest.factories.AbstractErrorFactoryTest;
import ca.ulaval.glo2003.transactions.exceptions.TransactionException;

public abstract class TransactionErrorFactoryTest
    extends AbstractErrorFactoryTest<TransactionException> {

  protected TransactionErrorFactoryTest(TransactionException associatedException) {
    super(associatedException, mock(TransactionException.class));
  }
}
