package ui;

import model.Flight;
import service.FlightService;
import service.PaymentService;

import javax.swing.*;
import java.util.ArrayList;

public class UserBooking {

    private FlightService flightService =
            new FlightService();

    private PaymentService paymentService =
            new PaymentService();

    private String loggedInUser;

    public UserBooking(String username) {

        this.loggedInUser = username;

        JFrame frame =
                new JFrame("User Booking Portal");

        frame.setSize(700, 600);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        JPanel panel = new JPanel();

        panel.setLayout(null);

        frame.add(panel);

        JLabel headerLabel =
                new JLabel(
                        "User Booking Portal - Book Flights"
                );

        headerLabel.setBounds(
                20,
                20,
                300,
                25
        );

        panel.add(headerLabel);

        // Source

        JLabel sourceLabel =
                new JLabel("Source:");

        sourceLabel.setBounds(
                20,
                60,
                100,
                25
        );

        panel.add(sourceLabel);

        JTextField sourceField =
                new JTextField();

        sourceField.setBounds(
                80,
                60,
                150,
                25
        );

        panel.add(sourceField);

        // Destination

        JLabel destinationLabel =
                new JLabel("Destination:");

        destinationLabel.setBounds(
                250,
                60,
                100,
                25
        );

        panel.add(destinationLabel);

        JTextField destinationField =
                new JTextField();

        destinationField.setBounds(
                340,
                60,
                150,
                25
        );

        panel.add(destinationField);

        // Search Button

        JButton searchButton =
                new JButton("Search Flights");

        searchButton.setBounds(
                520,
                60,
                150,
                25
        );

        panel.add(searchButton);

        // Flights Area

        JTextArea flightsArea =
                new JTextArea();

        flightsArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(flightsArea);

        scrollPane.setBounds(
                20,
                100,
                640,
                250
        );

        panel.add(scrollPane);

        // View All Flights Button

        JButton viewFlightsButton =
                new JButton("View All Flights");

        viewFlightsButton.setBounds(
                20,
                380,
                150,
                25
        );

        panel.add(viewFlightsButton);

        // Book Button

        JButton bookButton =
                new JButton("Book Flight");

        bookButton.setBounds(
                220,
                380,
                150,
                25
        );

        panel.add(bookButton);

        // Back Button

        JButton backButton =
                new JButton("Back");

        backButton.setBounds(
                420,
                380,
                100,
                25
        );

        panel.add(backButton);

        // Search Flights

        searchButton.addActionListener(e -> {

            String source =
                    sourceField.getText().trim();

            String destination =
                    destinationField.getText().trim();

            ArrayList<Flight> flights =
                    flightService.searchFlights(
                            source,
                            destination
                    );

            if (flights.isEmpty()) {

                flightsArea.setText(
                        "No Flights Found"
                );

                return;
            }

            StringBuilder result =
                    new StringBuilder();

            for (Flight flight : flights) {

                result.append(
                        "ID: "
                                + flight.getFlightId()
                                + " | "
                                + flight.getSource()
                                + " -> "
                                + flight.getDestination()
                                + " | Seats: "
                                + flight.getAvailableSeats()
                                + " | Price: $"
                                + flight.getPrice()
                                + "\n"
                );
            }

            flightsArea.setText(
                    result.toString()
            );
        });

        // View All Flights

        viewFlightsButton.addActionListener(e -> {

            ArrayList<Flight> flights =
                    flightService.getAllFlights();

            StringBuilder result =
                    new StringBuilder();

            for (Flight flight : flights) {

                result.append(
                        "ID: "
                                + flight.getFlightId()
                                + " | "
                                + flight.getSource()
                                + " -> "
                                + flight.getDestination()
                                + " | Seats: "
                                + flight.getAvailableSeats()
                                + " | Price: $"
                                + flight.getPrice()
                                + "\n"
                );
            }

            flightsArea.setText(
                    result.toString()
            );
        });

        // Book Flight

        bookButton.addActionListener(e -> {

            String flightId =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter Flight ID"
                    );

            Flight selectedFlight =
                    flightService.getFlightById(
                            flightId
                    );

            if (selectedFlight == null) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Flight ID"
                );

                return;
            }

            if (
                    selectedFlight.getAvailableSeats()
                            <= 0
            ) {

                JOptionPane.showMessageDialog(
                        frame,
                        "No Seats Available"
                );

                return;
            }

            // Payment Method

            String[] paymentMethods = {
                    "UPI",
                    "Card"
            };

            String paymentMethod =
                    (String) JOptionPane.showInputDialog(
                            frame,
                            "Choose Payment Method",
                            "Payment",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            paymentMethods,
                            paymentMethods[0]
                    );

            boolean paymentSuccess = false;

            // UPI Payment

            if ("UPI".equals(paymentMethod)) {

                String upiId =
                        JOptionPane.showInputDialog(
                                frame,
                                "Enter UPI ID"
                        );

                paymentSuccess =
                        paymentService.processUPIPayment(
                                upiId
                        );
            }

            // Card Payment

            else if (
                    "Card".equals(paymentMethod)
            ) {

                String cardNumber =
                        JOptionPane.showInputDialog(
                                frame,
                                "Enter Card Number"
                        );

                String cardHolderName =
                        JOptionPane.showInputDialog(
                                frame,
                                "Enter Card Holder Name"
                        );

                String cvv =
                        JOptionPane.showInputDialog(
                                frame,
                                "Enter CVV"
                        );

                paymentSuccess =
                        paymentService.processCardPayment(
                                cardNumber,
                                cardHolderName,
                                cvv
                        );
            }

            // Final Booking

            if (paymentSuccess) {

                selectedFlight.bookSeat();

                JOptionPane.showMessageDialog(
                        frame,
                        "Booking Successful!"
                                + "\nFlight ID: "
                                + selectedFlight.getFlightId()
                );
            }

            else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Payment Failed!"
                );
            }
        });

        // Back Button

        backButton.addActionListener(e -> {

            new UserLogin();

            frame.dispose();
        });

        frame.setVisible(true);
    }
}