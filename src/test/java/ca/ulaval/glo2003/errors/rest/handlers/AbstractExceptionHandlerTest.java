package ca.ulaval.glo2003.errors.rest.handlers;

import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.errors.rest.ErrorResponse;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

public abstract class AbstractExceptionHandlerTest<E extends Exception> {

  protected AbstractExceptionHandler<E> exceptionHandler;
  protected static ObjectMapper objectMapper = mock(ObjectMapper.class);
  private final E exception;
  private ErrorFactory<E> firstErrorFactory = mock(ErrorFactory.class);
  private ErrorFactory<E> secondErrorFactory = mock(ErrorFactory.class);
  protected Set<ErrorFactory<E>> factories =
      new HashSet<>(Arrays.asList(firstErrorFactory, secondErrorFactory));

  private static Request request = mock(Request.class);
  private static Response response = mock(Response.class);
  private static int firstStatus = 1;
  private static int secondStatus = 2;
  private static ErrorResponse firstResponse = new ErrorResponse();
  private static ErrorResponse secondResponse = new ErrorResponse();
  private static String firstBody = "firstBody";
  private static String secondBody = "secondBody";

  public AbstractExceptionHandlerTest(E exception) {
    this.exception = exception;
  }

  @BeforeEach
  public void setUpMocks() throws JsonProcessingException {
    when(firstErrorFactory.createStatus()).thenReturn(firstStatus);
    when(firstErrorFactory.createResponse()).thenReturn(firstResponse);
    when(objectMapper.writeValueAsString(firstResponse)).thenReturn(firstBody);
    when(firstErrorFactory.canHandle(exception)).thenReturn(true);
    when(secondErrorFactory.createStatus()).thenReturn(secondStatus);
    when(secondErrorFactory.createResponse()).thenReturn(secondResponse);
    when(objectMapper.writeValueAsString(secondResponse)).thenReturn(secondBody);
    when(secondErrorFactory.canHandle(exception)).thenReturn(false);
    reset(response);
    resetFactories();
  }

  protected abstract void resetFactories();

  @Test
  public void handle_shouldSetFirstStatus() {
    exceptionHandler.handle(exception, request, response);

    verify(response).status(firstStatus);
  }

  @Test
  public void handle_withFirstFactoryThatCannotHandle_shouldSetSecondStatus() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);
    when(secondErrorFactory.canHandle(exception)).thenReturn(true);

    exceptionHandler.handle(exception, request, response);

    verify(response).status(secondStatus);
  }

  @Test
  public void handle_withoutFactoryToHandle_shouldNotSetStatus() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);

    exceptionHandler.handle(exception, request, response);

    verify(response, never()).status();
  }

  @Test
  public void handle_shouldSetFirstBody() {
    exceptionHandler.handle(exception, request, response);

    verify(response).body(firstBody);
  }

  @Test
  public void handle_withFirstFactoryThatCannotHandle_shouldSetSecondBody() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);
    when(secondErrorFactory.canHandle(exception)).thenReturn(true);

    exceptionHandler.handle(exception, request, response);

    verify(response).body(secondBody);
  }

  @Test
  public void handle_withoutFactoryToHandle_shouldNotSetBody() {
    when(firstErrorFactory.canHandle(exception)).thenReturn(false);

    exceptionHandler.handle(exception, request, response);

    verify(response, never()).body();
  }
}
