package ca.ulaval.glo2003.transactions.rest.factories;

import ca.ulaval.glo2003.transactions.exceptions.OutOfBoundsServiceFeeException;

class OutOfBoundsServiceFeeErrorFactoryTest extends TransactionErrorFactoryTest {

  public OutOfBoundsServiceFeeErrorFactoryTest() {
    super(new OutOfBoundsServiceFeeException());
    errorFactory = new OutOfBoundsServiceFeeErrorFactory();
  }
}
