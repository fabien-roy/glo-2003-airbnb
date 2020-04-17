package ca.ulaval.glo2003.reports.rest;

import ca.ulaval.glo2003.interfaces.domain.AbstractMapper;
import ca.ulaval.glo2003.reports.rest.serializers.ReportDeserializingModule;
import ca.ulaval.glo2003.reports.rest.serializers.ReportSerializingModule;
import javax.inject.Inject;

public class ReportMapper extends AbstractMapper {

  @Inject
  public ReportMapper(
      ReportSerializingModule serializingModule, ReportDeserializingModule deserializingModule) {
    super(serializingModule, deserializingModule);
  }
}
