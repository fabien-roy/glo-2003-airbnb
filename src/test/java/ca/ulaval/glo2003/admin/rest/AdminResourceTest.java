package ca.ulaval.glo2003.admin.rest;

import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.admin.service.AdminService;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class AdminResourceTest {

  private static AdminResource adminResource;
  private static AdminService adminService = mock(AdminService.class);

  private static Request request = mock(Request.class);
  private static Response response = mock(Response.class);

  @BeforeAll
  public static void setUpResource() {
    adminResource = new AdminResource(adminService);
  }

  @BeforeEach
  public void setUpMocks() {
    reset(response);
  }

  @Test
  public void getAll_shouldCallAdminServiceDelete() {
    adminResource.deleteAll(request, response);

    verify(adminService).deleteAll();
  }

  @Test
  public void deleteAll_shouldSetOKAsHttpStatus() {
    adminResource.deleteAll(request, response);

    verify(response).status(HttpStatus.OK_200);
  }
}
