package ca.ulaval.glo2003.reports.domain;

import java.util.List;

public interface ReportQuery {

  List<ReportPeriod> execute();
}
