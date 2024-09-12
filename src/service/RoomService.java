package service;

import bean.Room;
import bean.enums.RoomType;
import repository.impl.RoomRepositoryImpl;

import java.util.Scanner;

public class RoomService {

    private  RoomRepositoryImpl roomRepositoryImpl;
    private Scanner scanner;

    public RoomService() {
        this.roomRepositoryImpl = new RoomRepositoryImpl();
        this.scanner = new Scanner(System.in);
    }

    public void saveRoom() {
        System.out.println("Enter the number of the room:");
        int nbrRoom = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the type of the room (e.g., SINGLE, DOUBLE, SUITE):");
        String roomTypeStr = scanner.nextLine();
        RoomType roomType = RoomType.valueOf(roomTypeStr.toUpperCase());

        boolean disponibility = true;
        Room room = new Room(0, nbrRoom, roomType, disponibility);
        roomRepositoryImpl.saveRoom(room);

        System.out.println("Room saved successfully!");
    }

    public Room updateRoom() {
        System.out.println("Enter the ID of the room:");
        int roomId = scanner.nextInt();
        scanner.nextLine();

        Room existingRoom = roomRepositoryImpl.getRoomById(roomId);
        if (existingRoom == null) {
            System.out.println("No room found with ID: " + roomId);
            return null;
        }

        System.out.println("Current room details: " + existingRoom);

        System.out.println("Enter the new number of the room:");
        int nbrRoom = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new type of the room (e.g., SINGLE, DOUBLE, SUITE):");
        String roomTypeStr = scanner.nextLine();
        RoomType roomType;
        try {
            roomType = RoomType.valueOf(roomTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid room type entered.");
            return null;
        }

        System.out.println("Is the room available (true/false)?");
        boolean disponibility = scanner.nextBoolean();

        Room updatedRoom = new Room(roomId, nbrRoom, roomType, disponibility);
        roomRepositoryImpl.updateRoom(updatedRoom);

        System.out.println("Room updated successfully!");
        return updatedRoom;
    }

    public void deleteRoom() {
        System.out.println("Enter the room ID to delete:");
        int roomId = scanner.nextInt();

        roomRepositoryImpl.deleteRoom(roomId);
        System.out.println("Room deleted successfully!");
    }

    public Room getRoomById() {
        System.out.println("Enter the room ID:");
        int roomId = scanner.nextInt();

        Room room = roomRepositoryImpl.getRoomById(roomId);
        if (room != null) {
            System.out.println("Room details: " + room);
        } else {
            System.out.println("No room found with ID: " + roomId);
        }
        return room;
    }
}