package ca.ulaval.glo2003.reports.domain.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class ReportMetricTest {

  protected static ReportMetric metric;
  protected static ReportPeriodData data;

  protected abstract ReportMetrics metricName();

  @BeforeEach
  public void setUpData() {
    setUpData(Collections.emptyList());
  }

  protected static void setUpData(List<Transaction> transactions) {
    data = new ReportPeriodData(transactions);
  }

  @Test
  public void calculate_shouldCalculateWithMetricName() {
    metric.calculate(data);

    assertEquals(1, data.getMetrics().size());
    assertEquals(metricName(), data.getMetrics().get(0).getName());
  }
}
