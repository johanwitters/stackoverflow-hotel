package com.johanw.stackoverflow.hotel.model.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum DayType {
    WEEKEND, WEEKDAY;

    public static DayType getType(LocalDate ld) {
        if ((ld.getDayOfWeek() == DayOfWeek.SATURDAY) || (ld.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            return WEEKEND;
        } else {
            return WEEKDAY;
        }
    }
}