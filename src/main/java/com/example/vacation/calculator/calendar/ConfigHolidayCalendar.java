package com.example.vacation.calculator.calendar;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "holiday-calendar")
public class ConfigHolidayCalendar implements HolidayCalendar {

    private Set<LocalDate> holidays = new HashSet<>(); // Можно бы было и как обычно private Set<LocalDate> holidays, но Spring не поймет, какая реализация нужна

    @Override
    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

    public void setHolidays(Set<LocalDate> holidays) {
        this.holidays = holidays;
    }

    public Set<LocalDate> getHolidays() {
        return holidays;
    }


}
