package bean;

import bean.enums.RoomType;
import bean.enums.Saison;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Room {
    private int id;
    private int nbrRoom;
    private RoomType type;
    private boolean disponibility;
    private BigDecimal basePrice;

    public Room(int nbrRoom, RoomType type, boolean disponibility, BigDecimal basePrice) {
        this.nbrRoom = nbrRoom;
        this.type = type;
        this.disponibility = disponibility;
        this.basePrice = basePrice;
    }

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

    public BigDecimal calculatePrice(LocalDate startDate, LocalDate endDate) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            Saison saison = getSeason(date);
            BigDecimal dailyPrice = basePrice
                    .multiply(getSeasonalRate(saison))
                    .multiply(getWeeklyRate(date.getDayOfWeek()))
                    .multiply(getSpecialEventRate(date));

            totalPrice = totalPrice.add(dailyPrice);
            date = date.plusDays(1);
        }

        return totalPrice;
    }

    private Saison getSeason(LocalDate date) {
        int month = date.getMonthValue();
        switch (month) {
            case 3: case 4: case 5:
                return Saison.SPRING;
            case 6: case 7: case 8:
                return Saison.SUMMER;
            case 9: case 10: case 11:
                return Saison.FALL;
            case 12: case 1: case 2:
                return Saison.WINTER;
            default:
                throw new IllegalArgumentException("Invalid month for season determination");
        }
    }

    private BigDecimal getSeasonalRate(Saison saison) {
        switch (saison) {
            case SPRING:
                return new BigDecimal("1.1");
            case SUMMER:
                return new BigDecimal("1.2");
            case FALL:
                return new BigDecimal("1.05");
            case WINTER:
                return new BigDecimal("0.9");
            default:
                throw new IllegalArgumentException("Unknown season: " + saison);
        }
    }

    private BigDecimal getWeeklyRate(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case SATURDAY:
            case SUNDAY:
                return new BigDecimal("1.15");
            default:
                return BigDecimal.ONE;
        }
    }

    private BigDecimal getSpecialEventRate(LocalDate date) {
        if (date.getMonth() == Month.DECEMBER && (date.getDayOfMonth() == 25 || date.getDayOfMonth() == 31)) {
            return new BigDecimal("1.25");
        }
        return BigDecimal.ONE;
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
