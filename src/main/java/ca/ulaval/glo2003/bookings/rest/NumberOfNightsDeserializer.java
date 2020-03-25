package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.parsers.rest.PositiveIntegerDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

public class NumberOfNightsDeserializer
    extends PositiveIntegerDeserializer<InvalidNumberOfNightsException> {

  @Override
  public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws InvalidNumberOfNightsException {
    int colonySize = super.deserialize(jsonParser, deserializationContext);

    if (colonySize > 90) throwException();

    return colonySize;
  }

  @Override
  public void throwException() throws InvalidNumberOfNightsException {
    throw new InvalidNumberOfNightsException();
  }
}
