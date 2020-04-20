package ca.ulaval.glo2003.reports.domain;

import java.util.List;

public interface ReportRepository<Q extends ReportQuery> {

  void addEvent(ReportEvent event);

  List<ReportPeriod> getPeriods(Q query);
}
