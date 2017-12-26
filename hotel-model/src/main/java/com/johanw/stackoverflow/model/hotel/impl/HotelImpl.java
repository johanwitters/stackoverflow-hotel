package com.johanw.stackoverflow.model.hotel.impl;

import com.johanw.stackoverflow.model.hotel.CustomerType;
import com.johanw.stackoverflow.model.hotel.Hotel;
import com.johanw.stackoverflow.model.hotel.StarRating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HotelImpl implements Hotel {
    static Logger logger = LoggerFactory.getLogger(HotelImpl.class);

    private String name;
    private StarRating rating;
    private Map<String, Price> prices;

    private HotelImpl(String name) {
        this.name = name;
    }

    public void setPrices(Price... prices) {
        this.prices = new HashMap<>();
        for (Price p : prices) {
            this.prices.put(p.key(), p);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public StarRating getRating() {
        return null;
    }

    // Use Supplier of Stream: https://stackoverflow.com/questions/23860533/copy-a-stream-to-avoid-stream-has-already-been-operated-upon-or-closed-java-8
    public Double getPrice(CustomerType customerType, List<LocalDate> dates) {
        // Best practice to convert an stream of strings to an object: https://stackoverflow.com/questions/24882927/using-java-8-to-convert-a-list-of-objects-into-a-string-obtained-from-the-tostri
        logger.info("getPrice for {}, for dates {}",
                customerType,
                dates.stream().map(Object::toString).collect(Collectors.joining(", "))
                );
        // Throw an exception in orElse branch : https://stackoverflow.com/questions/38571537/optional-in-orelse-branch-throws-exception
        // https://stackoverflow.com/questions/23860533/copy-a-stream-to-avoid-stream-has-already-been-operated-upon-or-closed-java-8
        return dates.stream().mapToDouble(
                ld -> getPrice(customerType, ld).orElseThrow(
                        NoPriceSpecifiedException::new).getPrice()
                ).sum();
    }

    public Optional<Price> getPrice(CustomerType customerType, LocalDate date) {
        DayType dt = DayType.getType(date);
        Price price = prices.get(Price.key(dt, customerType));
        return Optional.of(price);
    }

    // https://dzone.com/articles/design-patterns-the-builder-pattern
    public static class Builder {
        private String name;
        private StarRating rating;
        private Price[] prices;

        Builder(String name) {
            this.name = name;
        }

        public Builder withRating(StarRating rating) {
            this.rating = rating;
            return this;
        }

        public Builder withPrices(Price... prices) {
            this.prices = prices;
            return this;
        }

        public Hotel build() {
            HotelImpl hi = new HotelImpl(name);
            if (prices != null) hi.setPrices(prices);
            hi.rating = rating;
            return hi;
        }

        public static Builder newBuilder(String name) {
            return new Builder(name);
        }
    }
}
