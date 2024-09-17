import config.ConfigCon;
import service.ClientService;
import service.RoomService;
import service.ReservationService;
import service.HotelService;
import service.StatisticService; // Importer le service de statistiques
import ui.MainMenu;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║ Welcome to Hotel Management System        ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        try {
            Connection connection = ConfigCon.getInstance().getConnection();
            System.out.println("Connected to the database successfully!");

            // Initialize services
            ClientService clientService = new ClientService();
            RoomService roomService = new RoomService();
            ReservationService reservationService = new ReservationService();
            HotelService hotelService = new HotelService();
            StatisticService statisticService = new StatisticService(reservationService, roomService); // Initialize StatisticService

            // Initialize and display the main menu
            MainMenu mainMenu = new MainMenu(clientService, roomService, reservationService, hotelService, statisticService); // Pass StatisticService to MainMenu
            mainMenu.displayMainMenu();  // This will run the main menu loop

        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
