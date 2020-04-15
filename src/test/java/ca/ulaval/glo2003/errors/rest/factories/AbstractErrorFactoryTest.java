package ca.ulaval.glo2003.errors.rest.factories;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.errors.rest.ErrorResponse;
import org.junit.jupiter.api.Test;

public abstract class AbstractErrorFactoryTest<E extends Exception> {

  protected AbstractErrorFactory<E> errorFactory;
  private final E associatedException;
  private final E unrelatedException;

  protected AbstractErrorFactoryTest(E associatedException, E unrelatedException) {
    this.associatedException = associatedException;
    this.unrelatedException = unrelatedException;
  }

  @Test
  public void canHandle_withAssociatedException_shouldReturnTrue() {
    boolean result = errorFactory.canHandle(associatedException);

    assertTrue(result);
  }

  @Test
  public void canHandle_withoutAssociatedException_shouldReturnFalse() {
    boolean result = errorFactory.canHandle(unrelatedException);

    assertFalse(result);
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
