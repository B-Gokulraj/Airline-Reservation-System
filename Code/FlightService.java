package service;

import model.Flight;
import java.util.ArrayList;

public class FlightService {

    private ArrayList<Flight> flights =
            new ArrayList<>();

    public FlightService() {

        flights.add(
                new Flight(
                        "F101",
                        "Mumbai",
                        "Delhi",
                        50,
                        150.0
                )
        );

        flights.add(
                new Flight(
                        "F102",
                        "Chennai",
                        "Bangalore",
                        40,
                        120.0
                )
        );

        flights.add(
                new Flight(
                        "F103",
                        "Hyderabad",
                        "Pune",
                        60,
                        180.0
                )
        );

        flights.add(
                new Flight(
                        "F104",
                        "Delhi",
                        "Kolkata",
                        45,
                        170.0
                )
        );
    }

    // Get all flights

    public ArrayList<Flight> getAllFlights() {

        return flights;
    }

    // Search Flights

    public ArrayList<Flight> searchFlights(
            String source,
            String destination
    ) {

        ArrayList<Flight> result =
                new ArrayList<>();

        for (Flight flight : flights) {

            if (
                    flight.getSource()
                            .equalsIgnoreCase(source)

                            &&

                    flight.getDestination()
                            .equalsIgnoreCase(destination)
            ) {

                result.add(flight);
            }
        }

        return result;
    }

    // Get Flight By ID

    public Flight getFlightById(
            String flightId
    ) {

        for (Flight flight : flights) {

            if (
                    flight.getFlightId()
                            .equals(flightId)
            ) {

                return flight;
            }
        }

        return null;
    }

    // Remove Flight

    public boolean removeFlight(
            String flightId
    ) {

        return flights.removeIf(
                flight ->
                        flight.getFlightId()
                                .equals(flightId)
        );
    }

    // Update Flight

    public boolean updateFlight(
            String flightId,
            int seats,
            double price
    ) {

        Flight flight =
                getFlightById(flightId);

        if (flight != null) {

            flight.setAvailableSeats(seats);

            flight.setPrice(price);

            return true;
        }

        return false;
    }
}