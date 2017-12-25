package com.johanw.stackoverflow.hotel.model;

import java.time.LocalDate;

// Base class Hotel.
public interface Hotel  {
    String getName();
    StarRating getRating();

    Double getPrice(CustomerType customerType, LocalDate... date);
}
