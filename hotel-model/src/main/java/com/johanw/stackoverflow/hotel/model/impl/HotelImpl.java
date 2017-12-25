package com.johanw.stackoverflow.hotel.model.impl;

import com.johanw.stackoverflow.hotel.model.CustomerType;
import com.johanw.stackoverflow.hotel.model.Hotel;
import com.johanw.stackoverflow.hotel.model.StarRating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;

public class HotelImpl implements Hotel {
    static Logger logger = LoggerFactory.getLogger(HotelImpl.class);

    private String name;
    private StarRating rating;
    private Map<String, Price> prices;

    public HotelImpl(String name) {
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

    public Double getPrice(CustomerType customerType, LocalDate... date) {
        logger.info("getPrice for {}, for dates {}", customerType, Arrays.toString(date));
        double price = 0;
        for (int i = 0; i < date.length; i++) {
            LocalDate ld = date[i];
            Optional<Price> priceForDate = getPrice(customerType, ld);
            if (priceForDate.isPresent()) {
                price = price + priceForDate.get().getPrice();
            } else {
                throw new NoPriceSpecifiedException("No price available for " + customerType + " " + ld);
            }
        }
        return price;
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
