package ca.ulaval.glo2003.admin.service;

import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import javax.inject.Inject;

public class AdminService {
  private BedService bedService;
  private TransactionService transactionService;

  @Inject
  public AdminService(BedService bedService, TransactionService transactionService) {
    this.bedService = bedService;
    this.transactionService = transactionService;
  }

  public void deleteAll() {
    bedService.deleteAll();
    transactionService.deleteAll();
  };
}
