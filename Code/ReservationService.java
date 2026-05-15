package service;

import model.Reservation;
import java.util.ArrayList;

public class ReservationService {

    private ArrayList<Reservation> reservations =
            new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public ArrayList<Reservation> getReservationsByUser(
            String username) {

        ArrayList<Reservation> userReservations =
                new ArrayList<>();

        for (Reservation reservation : reservations) {

            if (reservation.getUsername().equals(username)) {
                userReservations.add(reservation);
            }
        }

        return userReservations;
    }
}