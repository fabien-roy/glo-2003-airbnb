package ca.ulaval.glo2003.admin;

import ca.ulaval.glo2003.admin.services.AdminService;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.reports.services.ReportService;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import com.google.inject.AbstractModule;

public class AdminModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AdminService.class);
    bind(BedService.class);
    bind(TransactionService.class);
    bind(ReportService.class);
  }
}
