package repository;

import bean.Hotel;

import java.util.List;

public abstract class HotelRepository {
    public abstract List<Hotel> getAllHotels() ;
    public abstract Hotel getHotelById(int HotelId);
    public abstract Hotel saveHotel(Hotel Hotel);
    public abstract void updateHotel(Hotel Hotel);
    public abstract void deleteHotel(int HotelId) ;
}
