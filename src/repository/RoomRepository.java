package repository;

import bean.Room;

import java.util.List;

public abstract class RoomRepository{
    public abstract List<Room> getAllRooms();
    public abstract Room getRoomById(int roomId);
    public abstract void saveRoom(Room room);
    public abstract void updateRoom(Room room);
    public abstract void deleteRoom(int roomId);
}
