package ca.ulaval.glo2003.reports;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo2003.reports.domain.ReportRepository;
import ca.ulaval.glo2003.reports.domain.assemblers.DimensionsQueryParamAssembler;
import ca.ulaval.glo2003.reports.domain.assemblers.MetricsQueryParamAssembler;
import ca.ulaval.glo2003.reports.domain.assemblers.ReportQueryParamAssembler;
import ca.ulaval.glo2003.reports.domain.assemblers.ScopeQueryParamAssembler;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo2003.reports.exceptions.ReportException;
import ca.ulaval.glo2003.reports.infrastructure.InMemoryReportQueryBuilder;
import ca.ulaval.glo2003.reports.infrastructure.InMemoryReportRepository;
import ca.ulaval.glo2003.reports.infrastructure.dimensions.InMemoryReportDimensionBuilder;
import ca.ulaval.glo2003.reports.infrastructure.metrics.InMemoryReportMetricBuilder;
import ca.ulaval.glo2003.reports.rest.ReportMapper;
import ca.ulaval.glo2003.reports.rest.ReportResource;
import ca.ulaval.glo2003.reports.rest.factories.InvalidReportDimensionsErrorFactory;
import ca.ulaval.glo2003.reports.rest.factories.InvalidReportMetricsErrorFactory;
import ca.ulaval.glo2003.reports.rest.factories.InvalidReportScopeErrorFactory;
import ca.ulaval.glo2003.reports.rest.handlers.ReportExceptionHandler;
import ca.ulaval.glo2003.reports.services.ReportService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class ReportModule extends AbstractModule {

  @Override
  protected void configure() {
    configureQueryParamAssemblers();
    configureErrorFactories();

    bind(ReportRepository.class).to(InMemoryReportRepository.class).in(Singleton.class);
    bind(ReportMetricBuilder.class).to(InMemoryReportMetricBuilder.class);
    bind(ReportDimensionBuilder.class).to(InMemoryReportDimensionBuilder.class);
    bind(ReportQueryBuilder.class).to(InMemoryReportQueryBuilder.class);
    bind(ReportQueryFactory.class);
    bind(ReportMapper.class);
    bind(ReportService.class);
    bind(ReportResource.class);
    bind(ReportExceptionHandler.class);
  }

  private void configureQueryParamAssemblers() {
    Multibinder<ReportQueryParamAssembler> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<ReportQueryParamAssembler>() {});
    multibinder.addBinding().to(ScopeQueryParamAssembler.class);
    multibinder.addBinding().to(MetricsQueryParamAssembler.class);
    multibinder.addBinding().to(DimensionsQueryParamAssembler.class);
  }

  private void configureErrorFactories() {
    Multibinder<ErrorFactory<ReportException>> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<ErrorFactory<ReportException>>() {});
    multibinder.addBinding().to(InvalidReportScopeErrorFactory.class);
    multibinder.addBinding().to(InvalidReportMetricsErrorFactory.class);
    multibinder.addBinding().to(InvalidReportDimensionsErrorFactory.class);
  }
}
