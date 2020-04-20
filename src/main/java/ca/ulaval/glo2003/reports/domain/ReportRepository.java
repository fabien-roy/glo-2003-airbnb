package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.beds.domain.Bed;

import java.util.List;

public interface ReportRepository<Q extends ReportQuery> {

  void add(ReportEvent event);

  List<Bed> getPeriods(Q query);
}
