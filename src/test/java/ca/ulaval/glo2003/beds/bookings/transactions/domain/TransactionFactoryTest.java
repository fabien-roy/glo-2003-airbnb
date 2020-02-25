package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import static ca.ulaval.glo2003.beds.bookings.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionFactoryTest {

  TransactionFactory transactionFactory;

  @BeforeEach
  public void setUpFactory() {
    transactionFactory = new TransactionFactory();
  }

  @Test
  public void create_shouldSetTimestamp() {
    Transaction transaction = aTransaction().build();

    transactionFactory.create(transaction);

    assertNotNull(transaction.getTimestamp());
  }
}
