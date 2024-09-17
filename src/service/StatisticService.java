package service;

import bean.Reservation;
import bean.Room;
import bean.enums.ReservationStatus;

import java.math.BigDecimal;
import java.util.List;

public class StatisticService {
    private ReservationService reservationService;
    private RoomService roomService;

    public StatisticService(ReservationService reservationService, RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    // Method to count cancelled reservations
    public void countCancelledReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        long cancelledCount = reservations.stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.CANCELLED)
                .count();

        System.out.println("Number of cancelled reservations: " + cancelledCount);
    }

    // Method to count confirmed reservations
    public void countConfirmedReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        long confirmedCount = reservations.stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.RESERVED)
                .count();

        System.out.println("Number of confirmed reservations: " + confirmedCount);
    }

    // Method to calculate the occupancy rate
    public void calculateOccupancyRate() {
        List<Reservation> reservations = reservationService.getAllReservations();
        List<Room> rooms = roomService.getAllRooms();
        long totalRooms = rooms.size();

        // Count unique rooms that have been reserved
        long reservedRoomsCount = reservations.stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.RESERVED)
                .map(Reservation::getRoom)
                .distinct()
                .count();

        double occupancyRate = ((double) reservedRoomsCount / totalRooms) * 100;
        System.out.println("Total rooms: " + totalRooms);
        System.out.println("Reserved rooms: " + reservedRoomsCount);
        System.out.println("Occupancy rate: " + occupancyRate + "%");
    }

    // Method to calculate total revenue from all reservations
    public void calculateTotalRevenue() {
        List<Reservation> reservations = reservationService.getAllReservations();

        // Sum the total price of all reserved reservations
        BigDecimal totalRevenue = reservations.stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.RESERVED)
                .map(Reservation::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total revenue generated: " + totalRevenue + "DH");
    }

    // Method to display all statistics together
    public void displayStatisticsReport() {
        System.out.println("=== Reservation Statistics Report ===");
        countCancelledReservations();
        countConfirmedReservations();
        calculateOccupancyRate();
        calculateTotalRevenue();
        System.out.println("====================================");
    }
}
