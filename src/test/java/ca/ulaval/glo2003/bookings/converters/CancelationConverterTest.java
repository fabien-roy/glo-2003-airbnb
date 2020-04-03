package ca.ulaval.glo2003.bookings.converters;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import ca.ulaval.glo2003.transactions.converters.TransactionConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo2003.transactions.domain.helpers.TransactionObjectMother.createTotal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CancelationConverterTest {
    private static CancelationConverter cancelationConverter;
    private static PriceConverter priceConverter = mock(PriceConverter.class);

    private static Price total = createTotal();

    @BeforeEach
    public void setUpMapper() {
        cancelationConverter = new CancelationConverter(priceConverter);
    }

    @BeforeEach
    public void setUpMock() {
        when(priceConverter.toDouble(total)).thenReturn(total.getValue().doubleValue());
    }

    @Test
    public void toResponse_shouldMapTotal() {
        CancelationResponse cancelationResponse = cancelationConverter.toResponse(total);

        assertEquals(total.getValue().doubleValue(), cancelationResponse.getRefundAmount());
    }

}