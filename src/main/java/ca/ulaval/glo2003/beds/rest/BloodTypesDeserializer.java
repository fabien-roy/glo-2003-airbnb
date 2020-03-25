package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.parsers.exceptions.BloodTypesParsingException;
import ca.ulaval.glo2003.parsers.rest.NonEmptyArrayDeserializer;

public class BloodTypesDeserializer extends NonEmptyArrayDeserializer<BloodTypesParsingException> {

  @Override
  public void throwException() throws BloodTypesParsingException {
    throw new BloodTypesParsingException();
  }
}
