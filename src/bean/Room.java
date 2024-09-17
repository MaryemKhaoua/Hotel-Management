package bean;

import bean.enums.RoomType;

import java.math.BigDecimal;

public class Room {
    private int id;
    private int nbrRoom;
    private RoomType type;
    private boolean disponibility;
    private BigDecimal basePrice;  // Changed from double to BigDecimal

    // Updated constructor to accept BigDecimal for basePrice
    public Room(int nbrRoom, RoomType type, boolean disponibility, BigDecimal basePrice) {
        this.nbrRoom = nbrRoom;
        this.type = type;
        this.disponibility = disponibility;
        this.basePrice = basePrice;
    }

    // Updated constructor to accept BigDecimal for basePrice
    public Room(int id, int nbrRoom, RoomType type, boolean disponibility, BigDecimal basePrice) {
        this.id = id;
        this.nbrRoom = nbrRoom;
        this.type = type;
        this.disponibility = disponibility;
        this.basePrice = basePrice;
    }

    public Room(int id, int nbrRoom, RoomType type, boolean disponibility) {
        this.id = id;
        this.nbrRoom = nbrRoom;
        this.type = type;
        this.disponibility = disponibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrRoom() {
        return nbrRoom;
    }

    public void setNbrRoom(int nbrRoom) {
        this.nbrRoom = nbrRoom;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }

    // Updated getter and setter to use BigDecimal
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public void reserve() {
        this.disponibility = false;
    }

    public void release() {
        this.disponibility = true;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", nbrRoom=" + nbrRoom +
                ", type=" + type +
                ", disponibility=" + disponibility +
                ", basePrice=" + basePrice +
                '}';
    }
}
