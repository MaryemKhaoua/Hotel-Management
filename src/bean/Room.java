package bean;

import bean.enums.RoomType;

public class Room {
    private int id;
    private int nbrRoom;
    private RoomType type;
    private boolean disponibility;

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
}
