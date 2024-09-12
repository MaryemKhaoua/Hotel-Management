package service;

import bean.Client;
import bean.Room;
import bean.Reservation;
import repository.impl.ClientRepositoryImpl;
import repository.impl.RoomRepositoryImpl;
import repository.impl.ReservationRepositoryImpl;

import java.time.LocalDate;
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

        Reservation reservation = new Reservation(client, room, startDate, endDate);
        reservationRepositoryImpl.saveReservation(reservation);
        System.out.println("Reservation saved successfully!");
        return reservation;
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

        Reservation reservation = new Reservation(reservationId, client, room, startDate, endDate);
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
