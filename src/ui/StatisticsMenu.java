package ui;

import service.StatisticService;

import java.util.Scanner;

public class StatisticsMenu {
    private StatisticService statisticService;
    private Scanner scanner;

    public StatisticsMenu(StatisticService statisticService) {
        this.statisticService = statisticService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║          STATISTICS MENU               ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. View Cancelled Reservations Count    ║");
            System.out.println("║ 2. View Confirmed Reservations Count    ║");
            System.out.println("║ 3. View Room Occupancy Rate             ║");
            System.out.println("║ 4. View Total Revenue Generated         ║");
            System.out.println("║ 5. View Complete Statistics Report      ║");
            System.out.println("║ 6. Exit                                 ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Please select an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    statisticService.countCancelledReservations();
                    break;
                case 2:
                    statisticService.countConfirmedReservations();
                    break;
                case 3:
                    statisticService.calculateOccupancyRate();
                    break;
                case 4:
                    statisticService.calculateTotalRevenue();
                    break;
                case 5:
                    statisticService.displayStatisticsReport();
                    break;
                case 6:
                    System.out.println("Exiting Statistics Menu...");
                    return;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }
}
