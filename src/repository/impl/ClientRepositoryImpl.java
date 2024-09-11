package repository.impl;

import bean.Client;
import config.ConfigCon;
import repository.ClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl extends ClientRepository {

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM client";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             Statement stm = cnx.createStatement();
             ResultSet res = stm.executeQuery(query)) {

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String email = res.getString("email");

                clients.add(new Client(id, name, email));
            }

        } catch (SQLException sqlException) {
            System.out.println("Error fetching clients: " + sqlException.getMessage());
        }

        return clients;
    }

    @Override
    public Client getClientById(int clientId) {
        Client client = null;
        String query = "SELECT * FROM clients WHERE id = ?";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstm = cnx.prepareStatement(query)) {
            pstm.setInt(1, clientId);

            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()) {
                    int id = res.getInt("id");
                    String name = res.getString("name");
                    String email = res.getString("email");

                    client = new Client(id, name, email);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Error fetching client by ID: " + sqlException.getMessage());
        }
        return client;
    }

    @Override
    public Client saveClient(Client client) {
        String query = "INSERT INTO clients (name, email) VALUES (?, ?)";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getEmail());
            pstmt.executeUpdate();

            System.out.println("Client saved successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error saving client: " + sqlException.getMessage());
        }

        return client;
    }

    @Override
    public void updateClient(Client client) {
        String query = "UPDATE clients SET name = ?, email = ? WHERE id = ?";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getEmail());
            pstmt.setInt(3, client.getId());
            pstmt.executeUpdate();

            System.out.println("Client updated successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error updating client: " + sqlException.getMessage());
        }
    }

    @Override
    public void deleteClient(int clientId) {
        String query = "DELETE FROM clients WHERE id = ?";

        try (Connection cnx = ConfigCon.getInstance().getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {

            pstmt.setInt(1, clientId);
            pstmt.executeUpdate();

            System.out.println("Client deleted successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error deleting client: " + sqlException.getMessage());
        }
    }
}