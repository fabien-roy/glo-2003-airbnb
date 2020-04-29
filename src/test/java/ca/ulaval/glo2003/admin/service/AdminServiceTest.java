package ca.ulaval.glo2003.admin.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdminServiceTest {

  private static AdminService adminService;
  private static BedService bedService = mock(BedService.class);
  public static TransactionService transactionService = mock(TransactionService.class);

  @BeforeAll
  public static void setUpResource() {
    adminService = new AdminService(bedService, transactionService);
  }

  @BeforeEach
  public void setUpMocks() {
    reset(bedService);
  }

  @Test
  public void deleteAll_shouldCallBedServiceDeleteAll() {
    adminService.deleteAll();

    verify(bedService).deleteAll();
  }

  @Test
  public void deleteAll_shouldCallTransactionServiceDeleteAll() {
    adminService.deleteAll();

    verify(transactionService).deleteAll();
  }
}
