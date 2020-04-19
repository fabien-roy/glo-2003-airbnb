package ca.ulaval.glo2003.reports.domain;

import static ca.ulaval.glo2003.time.domain.helpers.TimestampObjectMother.createTimestamp;
import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionBuilder.aTransaction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo2003.time.domain.Timestamp;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportQueryTest {

  private static ReportQuery query;

  private static ReportScope scope = mock(ReportScope.class);
  private static ReportPeriod firstPeriod = mock(ReportPeriod.class);
  private static ReportPeriod secondPeriod = mock(ReportPeriod.class);
  private static List<ReportMetric> metrics;
  private static ReportMetric firstMetric = mock(ReportMetric.class);
  private static ReportMetric secondMetric = mock(ReportMetric.class);
  private static List<ReportDimension> dimensions;
  private static ReportDimension firstDimension = mock(ReportDimension.class);
  private static ReportDimension secondDimension = mock(ReportDimension.class);
  private static List<Transaction> transactions;
  private static Timestamp timestamp = createTimestamp();
  private static Timestamp otherTimestamp = createTimestamp();
  private static Transaction firstTransaction = aTransaction().withTimestamp(timestamp).build();
  private static Transaction secondTransaction =
      aTransaction().withTimestamp(otherTimestamp).build();

  @BeforeAll
  private static void setUpPeriods() {
    when(firstPeriod.contains(timestamp)).thenReturn(true);
    when(firstPeriod.contains(otherTimestamp)).thenReturn(false);
    when(secondPeriod.contains(timestamp)).thenReturn(false);
    when(secondPeriod.contains(otherTimestamp)).thenReturn(true);
  }

  private void setUpQuery() {
    query = new ReportQuery(scope, metrics, dimensions);
    query.setTransactions(transactions);
  }

  @BeforeEach
  public void setUpMocks() {
    reset(scope);

    setUpScopeWithSinglePeriod();
    setUpScopeWithSingleDimension();
    setUpScopeWithSingleTransaction();
  }

  private void setUpScopeWithSinglePeriod() {
    when(scope.getReportPeriods()).thenReturn(Collections.singletonList(firstPeriod));
    setUpQuery();
  }

  private void setUpScopeWithMultiplePeriods() {
    when(scope.getReportPeriods()).thenReturn(Arrays.asList(firstPeriod, secondPeriod));
    setUpQuery();
  }

  private void setUpScopeWithSingleDimension() {
    dimensions = Collections.singletonList(firstDimension);
    setUpQuery();
  }

  private void setUpScopeWithSingleTransaction() {
    transactions = Collections.singletonList(firstTransaction);
    setUpQuery();
  }

  private void setUpScopeWithMultipleTransactions() {
    transactions = Arrays.asList(firstTransaction, secondTransaction);
    setUpQuery();
  }

  @Test
  public void execute_withSinglePeriod_shouldReturnSinglePeriod() {
    setUpScopeWithSinglePeriod();

    List<ReportPeriod> periods = query.execute();

    assertEquals(1, periods.size());
  }

  @Test
  public void execute_withMultiplePeriods_shouldReturnMultiplePeriods() {
    setUpScopeWithMultiplePeriods();

    List<ReportPeriod> periods = query.execute();

    assertEquals(2, periods.size());
  }

  @Test
  public void execute_shouldUseTransactionsWithinPeriods() {
    setUpScopeWithMultiplePeriods();
    setUpScopeWithMultipleTransactions();

    query.execute();

    verify(firstPeriod).setSingleData(eq(Collections.singletonList(firstTransaction)));
    verify(secondPeriod).setSingleData(eq(Collections.singletonList(secondTransaction)));
  }

  // TODO : Testing isn't done yet.
}
