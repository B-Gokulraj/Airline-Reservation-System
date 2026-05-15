package model;

public class Flight {private String flightId;private String source;private String destination;private int availableSeats;private double price;

// Constructor
public Flight(String flightId, String source, String destination, int availableSeats, double price) {
    this.flightId = flightId;
    this.source = source;
    this.destination = destination;
    this.availableSeats = availableSeats;
    this.price = price;
}

// Getters
public String getFlightId() {
    return flightId;
}

public String getSource() {
    return source;
}

public String getDestination() {
    return destination;
}

public int getAvailableSeats() {
    return availableSeats;
}

public double getPrice() {
    return price;
}

// Setters
public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
}

public void setPrice(double price) {
    this.price = price;
}

// Additional Methods
public void bookSeat() {
    if (availableSeats > 0) {
        availableSeats--;
    }
}

}