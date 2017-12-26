package com.johanw.stackoverflow.model.hotel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

// Base class Hotel.
public interface Hotel  {
    String getName();
    StarRating getRating();

    Double getPrice(CustomerType customerType, List<LocalDate> dates);
}
