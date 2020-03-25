package ca.ulaval.glo2003.parsers.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public abstract class AbstractParser extends ObjectMapper {

  public AbstractParser(List<DeserializingModule> modules) {
    modules.forEach(this::registerModule);
    disable(DeserializationFeature.WRAP_EXCEPTIONS);
  }
}
