package ca.ulaval.glo2003.beds.bookings.domain;

public enum BookingStatuses {
    BOOKED("booked"),
    CANCELED("canceled");

    private String status;

    BookingStatuses(String status) {this.status = status;}

    public String toString() {return status;}
}
