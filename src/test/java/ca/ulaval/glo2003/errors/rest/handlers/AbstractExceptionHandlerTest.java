package ca.ulaval.glo2003.errors.rest.handlers;

import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

class AbstractExceptionHandlerTest {

  private static ExceptionHandler<Exception> abstractExceptionHandler;
  private static ErrorFactory<Exception> firstErrorFactory = mock(ErrorFactory.class);
  private static ErrorFactory<Exception> secondErrorFactory = mock(ErrorFactory.class);
  private static Set<ErrorFactory<Exception>> factories =
      new HashSet<>(Arrays.asList(firstErrorFactory, secondErrorFactory));

  private static Exception exception = new Exception();
  private static Request request = mock(Request.class);
  private static Response response = mock(Response.class);
  private static int firstStatus = 1;
  private static int secondStatus = 2;
  private static String firstBody = "firstBody";
  private static String secondBody = "secondBody";

  @BeforeEach
  public void setUpMocks() {
    when(firstErrorFactory.createStatus()).thenReturn(firstStatus);
    when(firstErrorFactory.createResponse()).thenReturn(firstBody);
    when(firstErrorFactory.canHandle(exception)).thenReturn(true);
    when(secondErrorFactory.createStatus()).thenReturn(secondStatus);
    when(secondErrorFactory.createResponse()).thenReturn(secondBody);
    when(secondErrorFactory.canHandle(exception)).thenReturn(false);
    resetMocks();
  }

  private void resetMocks() {
    reset(response);
    abstractExceptionHandler = new FakeAbstractExceptionHandler(factories);
  }

  @Test
  public void handle_shouldSetFirstStatus() {
    abstractExceptionHandler.handle(exception, request, response);

    verify(response).status(firstStatus);
  }

  @Test
  public void handle_withFirstFactoryThatCannotHandle_shouldSetSecondStatus() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);
    when(secondErrorFactory.canHandle(exception)).thenReturn(true);

    abstractExceptionHandler.handle(exception, request, response);

    verify(response).status(secondStatus);
  }

  @Test
  public void handle_withoutFactoryToHandle_shouldNotSetStatus() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);

    abstractExceptionHandler.handle(exception, request, response);

    verify(response, never()).status();
  }

  @Test
  public void handle_shouldSetFirstBody() {
    abstractExceptionHandler.handle(exception, request, response);

    verify(response).body(firstBody);
  }

  @Test
  public void handle_withFirstFactoryThatCannotHandle_shouldSetSecondBody() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);
    when(secondErrorFactory.canHandle(exception)).thenReturn(true);

    abstractExceptionHandler.handle(exception, request, response);

    verify(response).body(secondBody);
  }

  @Test
  public void handle_withoutFactoryToHandle_shouldNotSetBody() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);

    abstractExceptionHandler.handle(exception, request, response);

    verify(response, never()).body();
  }
}
