package repository;

import bean.Reservation;

import java.util.List;

public abstract class ReservationRepository {
    public abstract List<Reservation> getAllReservations() ;
    public abstract Reservation getReservationById(int reservationId);
    public abstract Reservation saveReservation(Reservation reservation);
    public abstract void updateReservation(Reservation reservation);
    public abstract void deleteReservation(int reservationId) ;
}
