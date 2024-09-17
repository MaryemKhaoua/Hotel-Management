package repository.impl;

import bean.Room;
import bean.enums.RoomType;
import config.ConfigCon;
import repository.RoomRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl extends RoomRepository {

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             Statement stm = cnx.createStatement();
             ResultSet res = stm.executeQuery(query)) {

            while (res.next()) {
                int id = res.getInt("id");
                int nbrRoom = res.getInt("nbrRoom");
                String type = res.getString("type");
                BigDecimal basePrice = res.getBigDecimal("baseprice");
                boolean disponibility = res.getBoolean("disponibility");

                rooms.add(new Room(id, nbrRoom, RoomType.valueOf(type), disponibility,basePrice));
            }

        } catch (SQLException sqlException) {
            System.out.println("Error fetching rooms: " + sqlException.getMessage());
        }

        return rooms;
    }

    @Override
    public Room getRoomById(int roomId) {
        Room room = null;
        String query = "SELECT * FROM rooms WHERE id = ?";
        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstm = cnx.prepareStatement(query)) {

            pstm.setInt(1, roomId);

            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()) {
                    int id = res.getInt("id");
                    int nbrRoom = res.getInt("nbrRoom");
                    String type = res.getString("type");
                    BigDecimal basePrice = res.getBigDecimal("baseprice");
                    boolean disponibility = res.getBoolean("disponibility");

                    room = new Room(id, nbrRoom, RoomType.valueOf(type), disponibility,basePrice);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Error fetching room by ID: " + sqlException.getMessage());
        }
        return room;
    }

    @Override
    public void saveRoom(Room room) {
        String query = "INSERT INTO rooms (nbrroom, type, disponibility, baseprice) VALUES (?, ?, ?,?)";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setInt(1, room.getNbrRoom());
            pstmt.setString(2, room.getType().name());
            pstmt.setBoolean(3, room.isDisponibility());
            pstmt.setBigDecimal(4, room.getBasePrice());

            pstmt.executeUpdate();

            System.out.println("Room saved successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error saving room: " + sqlException.getMessage());
        }
    }

    @Override
    public void updateRoom(Room room) {
        String query = "UPDATE rooms SET nbrRoom = ?, type = ?, disponibility = ? WHERE id = ?";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setInt(1, room.getNbrRoom());
            pstmt.setString(2, room.getType().name());
            pstmt.setBoolean(3, room.isDisponibility());
            pstmt.setInt(4, room.getId());
            pstmt.executeUpdate();

            System.out.println("Room updated successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error updating room: " + sqlException.getMessage());
        }
    }

    @Override
    public void deleteRoom(int roomId) {
        String query = "DELETE FROM rooms WHERE id = ?";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setInt(1, roomId);
            pstmt.executeUpdate();

            System.out.println("Room deleted successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error deleting room: " + sqlException.getMessage());
        }
    }
}
