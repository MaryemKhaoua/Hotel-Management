package service;

import bean.Client;
import bean.Room;
import bean.Reservation;
import bean.enums.ReservationStatus;
import repository.impl.ClientRepositoryImpl;
import repository.impl.RoomRepositoryImpl;
import repository.impl.ReservationRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private ReservationRepositoryImpl reservationRepositoryImpl;
    private Scanner scanner;
    private ClientRepositoryImpl clientRepositoryImpl;
    private RoomRepositoryImpl roomRepositoryImpl;

    public ReservationService() {
        this.reservationRepositoryImpl = new ReservationRepositoryImpl();
        this.scanner = new Scanner(System.in);
        this.clientRepositoryImpl = new ClientRepositoryImpl();
        this.roomRepositoryImpl = new RoomRepositoryImpl();
    }

    public Room findRoom(int roomId, LocalDate startDate, LocalDate endDate) {
        List<Room> rooms = roomRepositoryImpl.getAllRooms();
        List<Reservation> reservations = reservationRepositoryImpl.getAllReservations();

        for (Room room : rooms) {
            if (room.getId() == roomId) {
                boolean isAvailable = true;

                for (Reservation reservation : reservations) {
                    if (reservation.getRoom().getId() == roomId) {
                        if (!(endDate.isBefore(reservation.getStartDate()) || startDate.isAfter(reservation.getEndDate()))) {
                            System.out.println("Room " + roomId + " is already booked from " +
                                    reservation.getStartDate() + " to " + reservation.getEndDate());
                            isAvailable = false;
                            break;
                        }
                    }
                }

                if (isAvailable) {
                    return room;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public Reservation saveReservation() {
        System.out.println("Enter the client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the room ID: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the start date (yyyy-mm-dd): ");
        String startDateInput = scanner.nextLine();

        System.out.println("Enter the end date (yyyy-mm-dd): ");
        String endDateInput = scanner.nextLine();

        LocalDate startDate = LocalDate.parse(startDateInput);
        LocalDate endDate = LocalDate.parse(endDateInput);

        Client client = clientRepositoryImpl.getClientById(clientId);
        Room room = findRoom(roomId, startDate, endDate);

        if (room == null) {
            System.out.println("Room is not available for the selected dates.");
            return null;
        }

        BigDecimal totalPrice = calculateTotalPrice(room, startDate, endDate);
        Reservation reservation = new Reservation(0, client, room, startDate, endDate, ReservationStatus.RESERVED, totalPrice);
        reservationRepositoryImpl.saveReservation(reservation);
        return reservation;
    }

    private BigDecimal calculateTotalPrice(Room room, LocalDate startDate, LocalDate endDate) {
        System.out.println(room);
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
        return room.getBasePrice().multiply(BigDecimal.valueOf(numberOfDays));
    }

    public void updateReservation() {
        System.out.println("Enter the reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the room ID: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the start date (yyyy-mm-dd): ");
        String startDateInput = scanner.nextLine();

        System.out.println("Enter the end date (yyyy-mm-dd): ");
        String endDateInput = scanner.nextLine();

        LocalDate startDate = LocalDate.parse(startDateInput);
        LocalDate endDate = LocalDate.parse(endDateInput);

        Client client = clientRepositoryImpl.getClientById(clientId);
        Room room = findRoom(roomId, startDate, endDate);

        if (room == null) {
            System.out.println("Room is not available for the selected dates.");
            return;
        }

        BigDecimal totalPrice = calculateTotalPrice(room, startDate, endDate);
        Reservation reservation = new Reservation(reservationId, client, room, startDate, endDate, ReservationStatus.RESERVED, totalPrice);
        reservationRepositoryImpl.updateReservation(reservation);
        System.out.println("Reservation updated successfully!");
    }

    public void cancelReservation() {
        System.out.println("Enter the reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        reservationRepositoryImpl.cancelReservation(reservationId);
        System.out.println("Reservation cancelled successfully!");
    }

    public Reservation getReservationById() {
        System.out.println("Enter the reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        return reservationRepositoryImpl.getReservationById(reservationId);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepositoryImpl.getAllReservations();
    }
}