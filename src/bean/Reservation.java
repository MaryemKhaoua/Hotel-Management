package bean;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private Client client;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(Client client, Room room, LocalDate startDate, LocalDate endDate) {
        this.client = client;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation(int id, Client client, Room room, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.client = client;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client=" + client +
                ", room=" + room +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
