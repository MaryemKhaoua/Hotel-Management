package bean;

import bean.enums.Days;
import bean.enums.Events;
import bean.enums.Saison;
import java.math.BigDecimal;

public class PrixDynamic {
    private Long id;
    private Saison season;
    private Days dayOfWeek;
    private Events event;
    private BigDecimal priceMultiplier;

    public PrixDynamic(Saison season, Days dayOfWeek, Events event, BigDecimal priceMultiplier) {
        this.season = season;
        this.dayOfWeek = dayOfWeek;
        this.event = event;
        this.priceMultiplier = priceMultiplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Saison getSeason() {
        return season;
    }

    public void setSeason(Saison season) {
        this.season = season;
    }

    public Days getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Days dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public BigDecimal getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(BigDecimal priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    @Override
    public String toString() {
        return "PrixDynamic{" +
                "id=" + id +
                ", season=" + season +
                ", dayOfWeek=" + dayOfWeek +
                ", event=" + event +
                ", priceMultiplier=" + priceMultiplier +
                '}';
    }
}