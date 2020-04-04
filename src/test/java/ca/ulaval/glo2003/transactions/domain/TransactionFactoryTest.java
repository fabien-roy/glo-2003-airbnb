package ca.ulaval.glo2003.transactions.domain;

import static ca.ulaval.glo2003.transactions.domain.helpers.TimestampBuilder.aTimestamp;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TransactionFactoryTest {

  private static TransactionFactory transactionFactory;

  private static String from = createFrom();
  private static String to = createTo();
  private static Price total = createTotal();
  private static Timestamp timestamp = aTimestamp().build();

  @BeforeAll
  public static void setUpFactory() {
    transactionFactory = new TransactionFactory();
  }

  @Test
  public void createStayBooked_shouldSetTimestamp() {
    Transaction transaction = transactionFactory.createStayBooked(from, to, total);

    assertNotNull(transaction.getTimestamp());
  }

  @Test
  public void createStayBooked_shouldSetFrom() {
    Transaction transaction = transactionFactory.createStayBooked(from, to, total);

    assertEquals(from, transaction.getFrom());
  }

  @Test
  public void createStayBooked_shouldSetTo() {
    Transaction transaction = transactionFactory.createStayBooked(from, to, total);

    assertEquals(to, transaction.getTo());
  }

  @Test
  public void createStayBooked_shouldSetTotal() {
    Transaction transaction = transactionFactory.createStayBooked(from, to, total);

    assertEquals(total, transaction.getTotal());
  }

  @Test
  public void createStayBooked_shouldSetReasonAsStayBooked() {
    TransactionReasons expectedReason = TransactionReasons.STAY_BOOKED;

    Transaction transaction = transactionFactory.createStayBooked(from, to, total);

    assertEquals(expectedReason, transaction.getReason());
  }

  @Test
  public void createStayCompleted_shouldSetTimestamp() {
    Transaction transaction = transactionFactory.createStayCompleted(from, to, total, timestamp);

    assertEquals(timestamp, transaction.getTimestamp());
  }

  @Test
  public void createStayCompleted_shouldSetFrom() {
    Transaction transaction = transactionFactory.createStayCompleted(from, to, total, timestamp);

    assertEquals(from, transaction.getFrom());
  }

  @Test
  public void createStayCompleted_shouldSetTo() {
    Transaction transaction = transactionFactory.createStayCompleted(from, to, total, timestamp);

    assertEquals(to, transaction.getTo());
  }

  @Test
  public void createStayCompleted_shouldSetTotal() {
    Transaction transaction = transactionFactory.createStayCompleted(from, to, total, timestamp);

    assertEquals(total, transaction.getTotal());
  }

  @Test
  public void createStayCompleted_shouldSetReasonAsStayCompleted() {
    TransactionReasons expectedReason = TransactionReasons.STAY_COMPLETED;

    Transaction transaction = transactionFactory.createStayCompleted(from, to, total, timestamp);

    assertEquals(expectedReason, transaction.getReason());
  }
}
