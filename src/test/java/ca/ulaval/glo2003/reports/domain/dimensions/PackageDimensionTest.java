package ca.ulaval.glo2003.reports.domain.dimensions;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.beds.domain.Packages;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Transaction;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class PackageDimensionTest extends ReportDimensionTest {

  private static Transaction bloodthirstyTransaction = mock(Transaction.class);
  private static Transaction allYouCanDrinkTransaction = mock(Transaction.class);
  private static Transaction sweetToothTransaction = mock(Transaction.class);

  @BeforeAll
  public static void setUpDimension() {
    dimension = new PackageDimension();
    setUpTransactions();
  }

  private static void setUpTransactions() {
    Booking bloodthirstyBooking = aBooking().withPackage(Packages.BLOODTHIRSTY).build();
    Booking allYouCanDrinkBooking = aBooking().withPackage(Packages.ALL_YOU_CAN_DRINK).build();
    Booking sweetToothBooking = aBooking().withPackage(Packages.SWEET_TOOTH).build();
    when(bloodthirstyTransaction.getBooking()).thenReturn(bloodthirstyBooking);
    when(allYouCanDrinkTransaction.getBooking()).thenReturn(allYouCanDrinkBooking);
    when(sweetToothTransaction.getBooking()).thenReturn(sweetToothBooking);
  }

  @Override
  protected List<Transaction> buildTransactions() {
    return Arrays.asList(bloodthirstyTransaction, allYouCanDrinkTransaction, sweetToothTransaction);
  }

  @Override
  protected int numberOfValues() {
    return Packages.values().length;
  }

  @ParameterizedTest
  @EnumSource(Packages.class)
  public void splitAll_shouldSplitWithPackageValues(Packages packageName) {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertTrue(
        splitData.stream()
            .anyMatch(data -> data.getDimensions().get(0).getValue().equals(packageName)));
  }

  @Test
  public void splitAll_shouldSplitWithPackageDimensionName() {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);

    assertTrue(
        splitData.stream()
            .allMatch(
                data -> data.getDimensions().get(0).getName().equals(ReportDimensions.PACKAGE)));
  }

  @ParameterizedTest
  @MethodSource("providePackageTransactions")
  public void splitAll_withSingleTransactionPerPackage_shouldSplitTransactionsByPackage(
      Packages value, Transaction transaction) {
    List<ReportPeriodData> splitData = dimension.splitAll(singleData);
    List<ReportPeriodData> filteredData = filterData(splitData, value);

    assertEquals(1, filteredData.size());
    assertEquals(1, filteredData.get(0).getTransactions().size());
    assertEquals(transaction, filteredData.get(0).getTransactions().get(0));
  }

  private static Stream<Arguments> providePackageTransactions() {
    return Stream.of(
        Arguments.of(Packages.BLOODTHIRSTY, bloodthirstyTransaction),
        Arguments.of(Packages.ALL_YOU_CAN_DRINK, allYouCanDrinkTransaction),
        Arguments.of(Packages.SWEET_TOOTH, sweetToothTransaction));
  }

  private List<ReportPeriodData> filterData(List<ReportPeriodData> data, Packages value) {
    return data.stream()
        .filter(datum -> datum.getDimensions().get(0).getValue().equals(value))
        .collect(Collectors.toList());
  }
}
