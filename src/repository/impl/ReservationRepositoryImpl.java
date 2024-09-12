package repository.impl;

import bean.Reservation;
import bean.Client;
import bean.Room;
import config.ConfigCon;
import repository.ReservationRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ReservationRepositoryImpl extends ReservationRepository {
    private final ClientRepositoryImpl clientRepositoryImpl;
    private final RoomRepositoryImpl roomRepositoryImpl;

    public ReservationRepositoryImpl() {
        clientRepositoryImpl = new ClientRepositoryImpl();
        roomRepositoryImpl = new RoomRepositoryImpl();
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Connection conn = ConfigCon.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int reservationId = rs.getInt("id");
                int clientId = rs.getInt("client_id");
                int roomId = rs.getInt("room_id");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();

                Client client = clientRepositoryImpl.getClientById(clientId);
                Room room = roomRepositoryImpl.getRoomById(roomId);

                if (client != null && room != null) {
                    Reservation reservation = new Reservation(reservationId, client, room, startDate, endDate);
                    reservations.add(reservation);
                } else {
                    System.err.println("Client or Room not found for reservation ID: " + reservationId);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all reservations", e);
        }

        return reservations;
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        Reservation reservation = null;
        String sql = "SELECT * FROM reservations WHERE id=?";

        try (Connection conn = ConfigCon.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reservationId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int clientId = rs.getInt("client_id");
                    int roomId = rs.getInt("room_id");
                    LocalDate startDate = rs.getDate("start_date").toLocalDate();
                    LocalDate endDate = rs.getDate("end_date").toLocalDate();

                    Client client = clientRepositoryImpl.getClientById(clientId);
                    Room room = roomRepositoryImpl.getRoomById(roomId);

                    if (client != null && room != null) {
                        reservation = new Reservation(reservationId, client, room, startDate, endDate);
                    } else {
                        System.err.println("Client or Room not found for reservation ID: " + reservationId);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching reservation by ID: " + reservationId, e);
        }

        return reservation;
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (client_id, room_id, start_date, end_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConfigCon.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, reservation.getClient().getId());
            ps.setInt(2, reservation.getRoom().getId());
            ps.setDate(3, Date.valueOf(reservation.getStartDate()));
            ps.setDate(4, Date.valueOf(reservation.getEndDate()));

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("Reservation saved successfully!");

        } catch (SQLException e) {
            throw new RuntimeException("Error saving reservation", e);
        }

        return reservation;
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET client_id=?, room_id=?, start_date=?, end_date=? WHERE id=?";

        try (Connection conn = ConfigCon.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reservation.getClient().getId());
            ps.setInt(2, reservation.getRoom().getId());
            ps.setDate(3, Date.valueOf(reservation.getStartDate()));
            ps.setDate(4, Date.valueOf(reservation.getEndDate()));
            ps.setInt(5, reservation.getId());

            ps.executeUpdate();
            System.out.println("Reservation updated successfully!");

        } catch (SQLException e) {
            throw new RuntimeException("Error updating reservation", e);
        }
    }

    @Override
    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservations WHERE id=?";

        try (Connection conn = ConfigCon.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reservationId);

            ps.executeUpdate();
            System.out.println("Reservation deleted successfully!");

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting reservation", e);
        }
    }

    public void cancelReservation(int reservationId) {
        String sql = "UPDATE reservations SET status='CANCELLED' WHERE id=?";

        try (Connection conn = ConfigCon.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reservationId);

            ps.executeUpdate();
            System.out.println("Reservation status updated to 'CANCELLED'");

        } catch (SQLException e) {
            throw new RuntimeException("Error canceling reservation", e);
        }
    }
}
