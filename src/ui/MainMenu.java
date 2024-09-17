package ui;

import service.ClientService;
import service.RoomService;
import service.ReservationService;
import service.HotelService;
import service.StatisticService;

import java.util.Scanner;

public class MainMenu {
    private ClientService clientService;
    private RoomService roomService;
    private ReservationService reservationService;
    private HotelService hotelService;
    private StatisticService statisticService;
    private Scanner scanner;

    public MainMenu(ClientService clientService, RoomService roomService, ReservationService reservationService, HotelService hotelService, StatisticService statisticService) {
        this.clientService = clientService;
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.hotelService = hotelService;
        this.statisticService = statisticService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║ Welcome to Hotel Management System      ║");
            System.out.println("╚═════════════════════════════════════════╝");
            System.out.println("1. ➤ Client Management");
            System.out.println("2. ➤ Room Management");
            System.out.println("3. ➤ Reservation Management");
            System.out.println("4. ➤ Hotel Management");
            System.out.println("5. ➤ Statistics Management");
            System.out.println("6. ➤ Exit");
            System.out.print("✦ Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    new ClientMenu(clientService).displayMenu();
                    break;
                case 2:
                    new RoomMenu(roomService).displayMenu();
                    break;
                case 3:
                    new ReservationMenu(reservationService).displayMenu();
                    break;
                case 4:
                    new HotelMenu(hotelService).displayMenu();
                    break;
                case 5:
                    new StatisticsMenu(statisticService).displayMenu();
                    break;
                case 6:
                    System.out.println("✦ Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("⚠ Invalid choice. Try again.");
            }
        }
    }
}
