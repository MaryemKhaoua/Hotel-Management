package bean;

import bean.enums.ReservationStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {
    private int id;
    private Client client;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;
    private BigDecimal totalPrice;

    public Reservation() {}

    public Reservation(Client client, Room room, LocalDate startDate, LocalDate endDate, ReservationStatus status) {
        this.client = client;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Reservation(int id, Client client, Room room, LocalDate startDate, LocalDate endDate, ReservationStatus status, BigDecimal totalPrice) {
        this.id = id;
        this.client = client;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.totalPrice = totalPrice;
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

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client=" + client +
                ", room=" + room +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                '}';
    }
}