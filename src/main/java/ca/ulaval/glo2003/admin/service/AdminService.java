package ca.ulaval.glo2003.admin.service;

import ca.ulaval.glo2003.beds.services.BedService;
import javax.inject.Inject;

public class AdminService {
  private BedService bedService;

  @Inject
  public AdminService(BedService bedService) {
    this.bedService = bedService;
  }

  public void deleteAll() {
    bedService.deleteAll();
  };
}
