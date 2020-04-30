package ca.ulaval.glo2003.transactions.rest.factories;

import ca.ulaval.glo2003.transactions.exceptions.InvalidServiceFeeException;

class InvalidServiceFeeErrorFactoryTest extends TransactionErrorFactoryTest {

  public InvalidServiceFeeErrorFactoryTest() {
    super(new InvalidServiceFeeException());
    errorFactory = new InvalidServiceFeeErrorFactory();
  }
}
