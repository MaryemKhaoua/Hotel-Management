package ui;

import bean.Reservation;
import service.ReservationService;

import java.util.Scanner;

public class ReservationMenu {
    private ReservationService reservationService;
    private Scanner scanner;

    public ReservationMenu(ReservationService reservationService) {
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            printHeader("Reservation Management Menu");
            System.out.println("1. ➤ Create Reservation");
            System.out.println("2. ➤ Update Reservation");
            System.out.println("3. ➤ Cancel Reservation");
            System.out.println("4. ➤ View Reservation by ID");
            System.out.println("5. ➤ View All Reservations");
            System.out.println("6. ➤ Back to Main Menu");
            System.out.print("✦ Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    reservationService.saveReservation();
                    break;
                case 2:
                    reservationService.updateReservation();
                    break;
                case 3:
                    reservationService.cancelReservation();
                    break;
                case 4:
                    Reservation reservation = reservationService.getReservationById();
                    System.out.println("Reservation ID: " + reservation.getId() + ", Client: " + reservation.getClient().getName());
                    break;
                case 5:
                    reservationService.getAllReservations().forEach(System.out::println);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("⚠ Invalid choice. Try again.");
            }
        }
    }

    private void printHeader(String title) {
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║ " + title);
        System.out.println("╚═════════════════════════════════════════╝\n");
    }
}
