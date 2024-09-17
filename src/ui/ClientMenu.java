package ui;

import bean.Client;
import service.ClientService;

import java.util.Scanner;

public class ClientMenu {
    private ClientService clientService;
    private Scanner scanner;

    public ClientMenu(ClientService clientService) {
        this.clientService = clientService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            printHeader("Client Management Menu");
            System.out.println("1. ➤ Create Client");
            System.out.println("2. ➤ Update Client");
            System.out.println("3. ➤ Delete Client by ID");
            System.out.println("4. ➤ View Client by ID");
            System.out.println("5. ➤ Back to Main Menu");
            System.out.print("✦ Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    clientService.saveClient();
                    break;
                case 2:
                    clientService.updateClient();
                    break;
                case 3:
                    clientService.deleteClient();
                    break;
                case 4:
                    Client client = clientService.getClientById();
                    System.out.println("Client Name: " + client.getName() + ", Email: " + client.getEmail());
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
