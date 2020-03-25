package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.parsers.exceptions.BloodTypesParsingException;
import ca.ulaval.glo2003.parsers.rest.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.util.ArrayList;
import java.util.List;

public class BloodTypesDeserializer
    extends AbstractDeserializer<String[], BloodTypesParsingException> {

  public BloodTypesDeserializer() {
    super(String[].class);
  }

  @Override
  public Class<?> getType() {
    return String[].class;
  }

  @Override
  public void throwException() throws BloodTypesParsingException {
    throw new BloodTypesParsingException();
  }

  @Override
  public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws BloodTypesParsingException {
    if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
      List<String> bloodTypes = new ArrayList<>();

      try {
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
          bloodTypes.add(jsonParser.getValueAsString());
        }
      } catch (Exception e) {
        throwException();
        return new String[0]; // TODO : Return nothing
      }

      return bloodTypes.toArray(new String[0]);
    }

    throwException();
    return new String[0]; // TODO : Return nothing
  }
}
