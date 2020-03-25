package ca.ulaval.glo2003.errors.rest.handlers;

import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

class DefaultExceptionHandlerTest {

  private static DefaultExceptionHandler<Exception> defaultExceptionHandler;
  private static DefaultErrorFactory defaultErrorFactory = mock(DefaultErrorFactory.class);
  private static ErrorFactory<Exception> firstErrorFactory = mock(ErrorFactory.class);
  private static Set<ErrorFactory<Exception>> factories =
      new HashSet<>(Collections.singletonList(firstErrorFactory));

  private static Exception exception = new Exception();
  private static Request request = mock(Request.class);
  private static Response response = mock(Response.class);
  private static int defaultStatus = 1;
  private static int firstStatus = 2;
  private static String defaultBody = "defaultBody";
  private static String firstBody = "firstBody";

  @BeforeEach
  public void setUpMocks() {
    when(defaultErrorFactory.createStatus()).thenReturn(defaultStatus);
    when(defaultErrorFactory.createResponse()).thenReturn(defaultBody);
    when(firstErrorFactory.createStatus()).thenReturn(firstStatus);
    when(firstErrorFactory.createResponse()).thenReturn(firstBody);
    when(firstErrorFactory.canHandle(exception)).thenReturn(true);
    resetMocks();
  }

  private void resetMocks() {
    reset(response);
    defaultExceptionHandler = new FakeDefaultExceptionHandler(defaultErrorFactory, factories);
  }

  @Test
  public void handle_shouldSetStatus() {
    defaultExceptionHandler.handle(exception, request, response);

    verify(response).status(firstStatus);
  }

  @Test
  public void handle_withoutFactories_shouldSetDefaultStatus() {
    defaultExceptionHandler =
        new FakeDefaultExceptionHandler(defaultErrorFactory, Collections.emptySet());

    defaultExceptionHandler.handle(exception, request, response);

    verify(response).status(defaultStatus);
  }

  @Test
  public void handle_withoutFactoryToHandle_shouldSetDefaultStatus() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);

    defaultExceptionHandler.handle(exception, request, response);

    verify(response).status(defaultStatus);
  }

  @Test
  public void handle_shouldSetBody() {
    defaultExceptionHandler.handle(exception, request, response);

    verify(response).body(firstBody);
  }

  @Test
  public void handle_withoutFactories_shouldSetDefaultBody() {
    defaultExceptionHandler =
        new FakeDefaultExceptionHandler(defaultErrorFactory, Collections.emptySet());

    defaultExceptionHandler.handle(exception, request, response);

    verify(response).body(defaultBody);
  }

  @Test
  public void handle_withoutFactoryToHandle_shouldSetDefaultBody() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);

    defaultExceptionHandler.handle(exception, request, response);

    verify(response).body(defaultBody);
  }
}
