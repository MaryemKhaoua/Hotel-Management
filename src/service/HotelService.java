package service;

import bean.Hotel;
import repository.impl.HotelRepositoryImpl;

import java.util.Scanner;

public class HotelService {
    private HotelRepositoryImpl hotelRepositoryImpl;
    private Scanner scanner;

    public HotelService() {
        this.hotelRepositoryImpl = new HotelRepositoryImpl();
        this.scanner = new Scanner(System.in);
    }

    public void saveHotel() {
        System.out.println("Enter Hotel name: ");
        String name = scanner.nextLine();

        Hotel hotel = new Hotel(name);  // No id required, as it's a new hotel
        hotelRepositoryImpl.saveHotel(hotel);
        System.out.println("Hotel saved successfully!");
    }

    public void updateHotel() {
        System.out.println("Enter Hotel id: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("Enter Hotel name: ");
        String name = scanner.nextLine();

        Hotel hotel = new Hotel(id, name);
        hotelRepositoryImpl.updateHotel(hotel);
        System.out.println("Hotel updated successfully!");
    }

    public void deleteHotel() {
        System.out.println("Enter Hotel id: ");
        int id = scanner.nextInt();
        hotelRepositoryImpl.deleteHotel(id);
        System.out.println("Hotel deleted successfully!");
    }

    public Hotel getHotelById() {
        System.out.println("Enter Hotel id: ");
        int id = scanner.nextInt();
        return hotelRepositoryImpl.getHotelById(id);
    }
}
