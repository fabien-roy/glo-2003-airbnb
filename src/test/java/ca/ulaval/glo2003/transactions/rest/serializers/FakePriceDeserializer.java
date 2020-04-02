package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.errors.exceptions.TestingException;

class FakePriceDeserializer extends PriceDeserializer<TestingException> {

  @Override
  public void throwException() throws TestingException {
    throw new TestingException();
  }
}
