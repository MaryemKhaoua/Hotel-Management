package repository.impl;

import bean.Hotel;
import config.ConfigCon;
import repository.HotelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryImpl extends HotelRepository {

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotels";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             Statement stm = cnx.createStatement();
             ResultSet res = stm.executeQuery(query)) {

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");

                hotels.add(new Hotel(id, name));
            }

        } catch (SQLException sqlException) {
            System.out.println("Error fetching hotels: " + sqlException.getMessage());
        }

        return hotels;
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        Hotel hotel = null;
        String query = "SELECT * FROM hotels WHERE id = ?";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstm = cnx.prepareStatement(query)) {

            pstm.setInt(1, hotelId);

            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()) {
                    int id = res.getInt("id");
                    String name = res.getString("name");

                    hotel = new Hotel(id, name);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Error fetching hotel by ID: " + sqlException.getMessage());
        }
        return hotel;
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String query = "INSERT INTO hotels (name) VALUES (?)";
        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setString(1, hotel.getName());
            pstmt.executeUpdate();

            System.out.println("Hotel saved successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error saving hotel: " + sqlException.getMessage());
        }

        return hotel;
    }

    @Override
    public void updateHotel(Hotel hotel) {
        String query = "UPDATE hotels SET name = ? WHERE id = ?";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setString(1, hotel.getName());
            pstmt.setInt(2, hotel.getId());
            pstmt.executeUpdate();

            System.out.println("Hotel updated successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error updating hotel: " + sqlException.getMessage());
        }
    }

    @Override
    public void deleteHotel(int hotelId) {
        String query = "DELETE FROM hotels WHERE id = ?";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setInt(1, hotelId);
            pstmt.executeUpdate();

            System.out.println("Hotel deleted successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error deleting hotel: " + sqlException.getMessage());
        }
    }
}
