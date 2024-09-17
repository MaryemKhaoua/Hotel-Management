package bean;

import java.time.LocalDate;

public class Event {
    private LocalDate startDate;
    private LocalDate endDate;
    private double priceFactor;

    public Event(LocalDate startDate, LocalDate endDate, double priceFactor) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceFactor = priceFactor;
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

    public double getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    @Override
    public String toString() {
        return "Event{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceFactor=" + priceFactor +
                '}';
    }
}