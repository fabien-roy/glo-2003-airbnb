package ca.ulaval.glo2003.reports.services;

import ca.ulaval.glo2003.reports.converters.ReportConverter;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.reports.domain.ReportQuery;
import ca.ulaval.glo2003.reports.domain.ReportQueryFactory;
import ca.ulaval.glo2003.reports.rest.ReportPeriodResponse;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;

public class ReportService {

  private final ReportQueryFactory reportQueryFactory;
  private final ReportConverter reportConverter;
  private final TransactionService transactionService;

  @Inject
  public ReportService(
      ReportQueryFactory reportQueryFactory,
      ReportConverter reportConverter,
      TransactionService transactionService) {
    this.reportQueryFactory = reportQueryFactory;
    this.reportConverter = reportConverter;
    this.transactionService = transactionService;
  }

  // TODO : Logic and tests of ReportService.getAll(...) (#331)
  public List<ReportPeriodResponse> getAll(Map<String, List<String>> params) {
    ReportQuery reportQuery = reportQueryFactory.create(params);

    List<Transaction> transactions = transactionService.getAll();
    reportQuery.setTransactions(transactions);

    List<ReportPeriod> reportPeriods = reportQuery.execute();
    return reportConverter.toResponses(reportPeriods);
  }
}
