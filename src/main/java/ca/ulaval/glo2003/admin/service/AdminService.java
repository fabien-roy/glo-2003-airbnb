package ca.ulaval.glo2003.admin.service;

import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.reports.services.ReportService;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import javax.inject.Inject;

public class AdminService {
  private BedService bedService;
  private TransactionService transactionService;
  private ReportService reportService;

  @Inject
  public AdminService(
      BedService bedService, TransactionService transactionService, ReportService reportService) {
    this.bedService = bedService;
    this.transactionService = transactionService;
    this.reportService = reportService;
  }

  public void deleteAll() {
    bedService.deleteAll();
    transactionService.deleteAll();
    reportService.deleteAll();
  }
}
