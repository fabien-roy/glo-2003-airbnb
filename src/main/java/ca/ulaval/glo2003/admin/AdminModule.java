package ca.ulaval.glo2003.admin;

import ca.ulaval.glo2003.admin.service.AdminService;
import ca.ulaval.glo2003.beds.services.BedService;
import com.google.inject.AbstractModule;

public class AdminModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AdminService.class);
    bind(BedService.class);
  }
}
