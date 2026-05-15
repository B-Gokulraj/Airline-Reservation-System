package model;

public class Reservation {

    private String reservationId;
    private String flightId;
    private String username;
    private double amountPaid;

    // Constructor
    public Reservation(String reservationId,
                       String flightId,
                       String username,
                       double amountPaid) {

        this.reservationId = reservationId;
        this.flightId = flightId;
        this.username = username;
        this.amountPaid = amountPaid;
    }

    // Getter methods

    public String getReservationId() {
        return reservationId;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getUsername() {
        return username;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}