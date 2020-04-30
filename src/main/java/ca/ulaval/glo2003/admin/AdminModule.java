package ca.ulaval.glo2003.admin;

import ca.ulaval.glo2003.admin.service.AdminService;
import com.google.inject.AbstractModule;

public class AdminModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(AdminService.class);
  }
}
