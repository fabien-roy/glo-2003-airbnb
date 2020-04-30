package ca.ulaval.glo2003.admin.rest;

import static spark.Spark.delete;

import ca.ulaval.glo2003.admin.services.AdminService;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class AdminResource implements RouteGroup {

  public static final String ADMIN_PATH = "/admin";

  private final AdminService adminService;

  @Inject
  public AdminResource(AdminService adminService) {
    this.adminService = adminService;
  }

  @Override
  public void addRoutes() {
    delete("", this::deleteAll);
  }

  public Object deleteAll(Request request, Response response) {
    adminService.deleteAll();
    response.status(HttpStatus.OK_200);

    return 0;
  }
}
