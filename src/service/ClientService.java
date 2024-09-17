package service;

import bean.Client;
import repository.impl.ClientRepositoryImpl;

import java.util.Scanner;

public class ClientService {
    private ClientRepositoryImpl clientRepositoryImpl;
    private Scanner scanner;

    public ClientService() {
        this.clientRepositoryImpl = new ClientRepositoryImpl();
        this.scanner = new Scanner(System.in);
    }
    public void saveClient()  {
        System.out.println("Enter Client name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Client Email: ");
        String email = scanner.nextLine();

        Client client = new Client(name, email);
        clientRepositoryImpl.saveClient(client);
        System.out.println("Client saved successfully!");
    }

    public void updateClient()  {
        System.out.println("Enter Client id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Client name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Client Email: ");
        String email = scanner.nextLine();

        Client client = new Client(id, name, email);
        clientRepositoryImpl.updateClient(client);
        System.out.println("Client updated successfully!");
    }

    public void deleteClient()  {
        System.out.println("Enter Client id: ");
        int id = scanner.nextInt();
        clientRepositoryImpl.deleteClient(id);
        System.out.println("Client deleted successfully!");
    }

    public Client getClientById() {
        System.out.println("Enter Client id: ");
        int id = scanner.nextInt();

        Client client = clientRepositoryImpl.getClientById(id);
        if (client != null) {
            System.out.println("Client ID: " + client.getId());
            System.out.println("Client Name: " + client.getName());
            System.out.println("Client Email: " + client.getEmail());
        } else {
            System.out.println("Client not found!");
        }

        return client;
    }
}