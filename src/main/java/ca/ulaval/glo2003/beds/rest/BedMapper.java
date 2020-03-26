package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.rest.serializers.BedDeserializingModule;
import ca.ulaval.glo2003.beds.rest.serializers.BedSerializingModule;
import ca.ulaval.glo2003.parsers.domain.AbstractMapper;
import javax.inject.Inject;

public class BedMapper extends AbstractMapper {

  @Inject
  public BedMapper(
      BedSerializingModule serializingModule, BedDeserializingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
