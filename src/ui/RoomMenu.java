package ui;

import bean.Room;
import service.RoomService;

import java.util.Scanner;

public class RoomMenu {
    private RoomService roomService;
    private Scanner scanner;

    public RoomMenu(RoomService roomService) {
        this.roomService = roomService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            printHeader("Room Management Menu");
            System.out.println("1. ➤ Create Room");
            System.out.println("2. ➤ Update Room");
            System.out.println("3. ➤ Delete Room by ID");
            System.out.println("4. ➤ View Room by ID");
            System.out.println("5. ➤ Back to Main Menu");
            System.out.print("✦ Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    roomService.saveRoom();
                    break;
                case 2:
                    roomService.updateRoom();
                    break;
                case 3:
                    roomService.deleteRoom();
                    break;
                case 4:
                    Room room = roomService.getRoomById();
                    System.out.println("Room Number: " + room.getNbrRoom());
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
