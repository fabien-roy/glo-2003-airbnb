package ca.ulaval.glo2003.parsers.rest.serializers;

import ca.ulaval.glo2003.errors.exceptions.TestingException;

class FakeStringDeserializer extends StringDeserializer<TestingException> {

  @Override
  public void throwException() throws TestingException {
    throw new TestingException();
  }
}
