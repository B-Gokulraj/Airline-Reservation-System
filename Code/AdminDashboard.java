package ui;

import model.Flight;import service.FlightService;

import javax.swing.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;

public class AdminDashboard {private FlightService flightService = new FlightService();

public AdminDashboard() {
    JFrame frame = new JFrame("Admin Dashboard");
    frame.setSize(700, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(null);
    frame.add(panel);

    JLabel headerLabel = new JLabel("Admin Dashboard - Manage Flights");
    headerLabel.setBounds(20, 20, 300, 25);
    panel.add(headerLabel);

    JTextArea flightsArea = new JTextArea();
    flightsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(flightsArea);
    scrollPane.setBounds(20, 60, 640, 300);
    panel.add(scrollPane);

    JButton viewFlightsButton = new JButton("View All Flights");
    viewFlightsButton.setBounds(20, 380, 150, 25);
    panel.add(viewFlightsButton);

    JButton cancelFlightButton = new JButton("Cancel Flight");
    cancelFlightButton.setBounds(200, 380, 150, 25);
    panel.add(cancelFlightButton);

    JButton updateFlightButton = new JButton("Update Flight");
    updateFlightButton.setBounds(380, 380, 150, 25);
    panel.add(updateFlightButton);

    JButton backButton = new JButton("Back");
    backButton.setBounds(560, 380, 100, 25);
    panel.add(backButton);

    viewFlightsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder flightsData = new StringBuilder("All Flights:\n");
            for (Flight flight : flightService.getAllFlights()) {
                flightsData.append("ID: ").append(flight.getFlightId())
                        .append(", Source: ").append(flight.getSource())
                        .append(", Destination: ").append(flight.getDestination())
                        .append(", Seats: ").append(flight.getAvailableSeats())
                        .append(", Price: $").append(flight.getPrice())
                        .append("\n");
            }
            flightsArea.setText(flightsData.toString());
        }
    });

    cancelFlightButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String flightId = JOptionPane.showInputDialog("Enter Flight ID to Cancel:");
            if (flightId == null || flightId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Flight ID cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean removed = flightService.removeFlight(flightId);
            if (removed) {
                JOptionPane.showMessageDialog(frame, "Flight canceled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Flight not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    updateFlightButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String flightId = JOptionPane.showInputDialog("Enter Flight ID to Update:");
            if (flightId == null || flightId.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Flight ID cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Flight flight = flightService.getFlightById(flightId);
            if (flight != null) {
                String newSeats = JOptionPane.showInputDialog("Enter New Number of Seats (current: " + flight.getAvailableSeats() + "):");
                String newPrice = JOptionPane.showInputDialog("Enter New Price (current: $" + flight.getPrice() + "):");

                try {
                    int seats = Integer.parseInt(newSeats);
                    double price = Double.parseDouble(newPrice);

                    flight.setAvailableSeats(seats);
                    flight.setPrice(price);

                    JOptionPane.showMessageDialog(frame, "Flight updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input for seats or price!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Flight not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    backButton.addActionListener(e -> {
        new AdminLogin();
        frame.dispose();
    });

    frame.setVisible(true);
}

}