package ui;

import bean.Hotel;
import service.HotelService;

import java.util.Scanner;

public class HotelMenu {
    private HotelService hotelService;
    private Scanner scanner;

    public HotelMenu(HotelService hotelService) {
        this.hotelService = hotelService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            printHeader("Hotel Management Menu");
            System.out.println("1. ➤ Add New Hotel");
            System.out.println("2. ➤ Update Hotel Information");
            System.out.println("3. ➤ Delete Hotel by ID");
            System.out.println("4. ➤ View Hotel by ID");
            System.out.println("5. ➤ Back to Main Menu");
            System.out.print("✦ Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    hotelService.saveHotel();
                    break;
                case 2:
                    hotelService.updateHotel();
                    break;
                case 3:
                    hotelService.deleteHotel();
                    break;
                case 4:
                    Hotel hotel = hotelService.getHotelById();
                    System.out.println("Hotel Name: " + hotel.getName());
                    break;
                case 5:
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
