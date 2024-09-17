package service;

import bean.enums.Saison;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class PricingRules {

    private final BigDecimal springRate = new BigDecimal("1.10");
    private final BigDecimal summerRate = new BigDecimal("1.20");
    private final BigDecimal fallRate = new BigDecimal("1.05");
    private final BigDecimal winterRate = new BigDecimal("0.90");

    private final BigDecimal weekendRate = new BigDecimal("1.15");
    private final BigDecimal weekdayRate = BigDecimal.ONE;

    private final BigDecimal specialEventRate = new BigDecimal("1.25");

    public BigDecimal getSeasonalRate(Saison saison) {
        switch (saison) {
            case SPRING:
                return springRate;
            case SUMMER:
                return summerRate;
            case FALL:
                return fallRate;
            case WINTER:
                return winterRate;
            default:
                throw new IllegalArgumentException("Invalid season: " + saison);
        }
    }

    public BigDecimal getWeeklyRate(DayOfWeek dayOfWeek) {
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) ? weekendRate : weekdayRate;
    }

    public BigDecimal getSpecialEventRate(LocalDate date) {
        return specialEventRate;
    }
}
