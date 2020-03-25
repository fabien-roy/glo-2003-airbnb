package ca.ulaval.glo2003.errors.rest.handlers;

import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class CatchallExceptionHandlerTest {

  private static CatchallExceptionHandler catchallExceptionHandler;
  private static DefaultErrorFactory defaultErrorFactory = mock(DefaultErrorFactory.class);

  private static Exception exception = new Exception();
  private static Request request = mock(Request.class);
  private static Response response = mock(Response.class);
  private static int defaultStatus = 1;
  private static String defaultBody = "defaultBody";

  @BeforeAll
  public static void setUpHandler() {
    catchallExceptionHandler = new CatchallExceptionHandler(defaultErrorFactory);
  }

  @BeforeEach
  public void setUpMocks() {
    when(defaultErrorFactory.createStatus()).thenReturn(defaultStatus);
    when(defaultErrorFactory.createResponse()).thenReturn(defaultBody);
    resetMocks();
  }

  private void resetMocks() {
    reset(response);
  }

  @Test
  public void handle_shouldSetStatus() {
    catchallExceptionHandler.handle(exception, request, response);

    verify(response).status(defaultStatus);
  }

  @Test
  public void handle_shouldSetBody() {
    catchallExceptionHandler.handle(exception, request, response);

    verify(response).body(defaultBody);
  }
}
