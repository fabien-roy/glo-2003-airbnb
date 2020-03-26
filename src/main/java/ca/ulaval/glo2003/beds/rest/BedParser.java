package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.rest.serializers.BedDeserializingModule;
import ca.ulaval.glo2003.beds.rest.serializers.BedSerializingModule;
import ca.ulaval.glo2003.parsers.domain.AbstractParser;
import javax.inject.Inject;

public class BedParser extends AbstractParser {

  @Inject
  public BedParser(
      BedSerializingModule serializingModule, BedDeserializingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
