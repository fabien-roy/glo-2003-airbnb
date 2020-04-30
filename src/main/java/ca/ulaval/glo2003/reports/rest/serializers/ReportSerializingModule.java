package ca.ulaval.glo2003.reports.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.SerializingModule;
import java.util.Arrays;
import javax.inject.Inject;

public class ReportSerializingModule extends SerializingModule {

  @Inject
  public ReportSerializingModule(
      IntegerReportMetricDataValueSerializer integerReportMetricDataValueSerializer,
      DoubleReportMetricDataValueSerializer doubleReportMetricDataValueSerializer) {
    super(
        Arrays.asList(
            integerReportMetricDataValueSerializer, doubleReportMetricDataValueSerializer));
  }
}
