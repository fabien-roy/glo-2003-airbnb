package ca.ulaval.glo2003.beds.bookings.transactions.rest;

import ca.ulaval.glo2003.beds.bookings.transactions.services.TransactionService;

public class TransactionResource {
    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

}
