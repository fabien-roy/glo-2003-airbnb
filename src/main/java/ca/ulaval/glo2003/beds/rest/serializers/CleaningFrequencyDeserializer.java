package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidCleaningFrequencyException;
import ca.ulaval.glo2003.interfaces.rest.serializers.StringDeserializer;

public class CleaningFrequencyDeserializer
    extends StringDeserializer<InvalidCleaningFrequencyException> {

  @Override
  public void throwException() throws InvalidCleaningFrequencyException {
    throw new InvalidCleaningFrequencyException();
  }
}
