package ca.ulaval.glo2003.errors.rest.factories;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.errors.rest.ErrorResponse;
import org.junit.jupiter.api.Test;

public abstract class CatchallErrorFactoryTest {

  protected AbstractErrorFactory<Exception> errorFactory;
  private final Exception associatedException;

  public CatchallErrorFactoryTest(Exception associatedException) {
    this.associatedException = associatedException;
  }

  @Test
  public void canHandle_withAssociatedException_shouldReturnTrue() {
    boolean result = errorFactory.canHandle(associatedException);

    assertTrue(result);
  }

  @Test
  public void createResponse_shouldMapError() {
    ErrorResponse errorResponse = errorFactory.createResponse();

    assertEquals(errorFactory.getError(), errorResponse.getError());
  }

  @Test
  public void createResponse_shouldMapDescription() {
    ErrorResponse errorResponse = errorFactory.createResponse();

    assertEquals(errorFactory.getDescription(), errorResponse.getDescription());
  }

  @Test
  public void createStatus_shouldMapStatus() {
    int actualStatus = errorFactory.createStatus();

    assertEquals(errorFactory.getStatus(), actualStatus);
  }
}
