package ca.ulaval.glo2003.transactions.domain;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.*;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionFactoryTest {

  TransactionFactory transactionFactory;

  @BeforeEach
  public void setUpFactory() {
    transactionFactory = new TransactionFactory();
  }

  @Test
  public void createStayBooked_shouldSetTimestamp() {
    Transaction transaction = transactionFactory.createStayBooked(createFrom(), createTotal());

    assertNotNull(transaction.getTimestamp());
  }

  @Test
  public void createStayBooked_shouldSetFrom() {
    String expectedFrom = createFrom();

    Transaction transaction = transactionFactory.createStayBooked(expectedFrom, createTotal());

    assertEquals(expectedFrom, transaction.getFrom());
  }

  @Test
  public void createStayBooked_shouldSetToAsAirbnb() {
    String expectedTo = TransactionFactory.AIRBNB;

    Transaction transaction = transactionFactory.createStayBooked(createFrom(), createTotal());

    assertEquals(expectedTo, transaction.getTo());
  }

  @Test
  public void createStayBooked_shouldSetTotal() {
    Price expectedTotal = createTotal();

    Transaction transaction = transactionFactory.createStayBooked(createFrom(), expectedTotal);

    assertEquals(expectedTotal, transaction.getTotal());
  }

  @Test
  public void createStayBooked_shouldSetReasonAsStayBooked() {
    TransactionReasons expectedReason = TransactionReasons.STAY_BOOKED;

    Transaction transaction = transactionFactory.createStayBooked(createFrom(), createTotal());

    assertEquals(expectedReason, transaction.getReason());
  }

  @Test
  public void createStayCompleted_shouldSetTimestamp() {
    Transaction transaction = transactionFactory.createStayCompleted(createTo(), createTotal(), 1);

    assertNotNull(transaction.getTimestamp());
  }

  @Test
  public void createStayCompleted_shouldSetFromAsAirbnb() {
    String expectedFrom = TransactionFactory.AIRBNB;

    Transaction transaction =
        transactionFactory.createStayCompleted(expectedFrom, createTotal(), 1);

    assertEquals(expectedFrom, transaction.getFrom());
  }

  @Test
  public void createStayCompleted_shouldSetTo() {
    String expectedTo = createTo();

    Transaction transaction = transactionFactory.createStayCompleted(expectedTo, createTotal(), 1);

    assertEquals(expectedTo, transaction.getTo());
  }

  @Test
  public void createStayCompleted_shouldSetTotal() {
    Price expectedTotal = createTotal();

    Transaction transaction = transactionFactory.createStayCompleted(createTo(), expectedTotal, 1);

    assertEquals(expectedTotal, transaction.getTotal());
  }

  @Test
  public void createStayCompleted_shouldSetReasonAsStayCompleted() {
    TransactionReasons expectedReason = TransactionReasons.STAY_COMPLETED;

    Transaction transaction = transactionFactory.createStayCompleted(createTo(), createTotal(), 1);

    assertEquals(expectedReason, transaction.getReason());
  }
}
