package com.johanw.stackoverflow.hotel.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Dealer {
    private Hotel[] hotels;

    public Dealer(Hotel[] hotels) {
        this.hotels = hotels;
    }

    public static class Deal {
        private Hotel hotel;
        private Double price;

        public Deal(Hotel hotel, Double price) {
            this.hotel = hotel;
            this.price = price;
        }

        public Hotel getHotel() {
            return hotel;
        }

        public Double getPrice() {
            return price;
        }
    }

    // Pattern: use Optional as described at https://community.oracle.com/docs/DOC-991686
    public Optional<Deal> getCheapest(CustomerType ct, LocalDate... dates) {
        Deal bestDeal = null;
        for (Hotel hotel : hotels) {
            double currentPrice = hotel.getPrice(ct, dates);
            if ((bestDeal == null) || (currentPrice < bestDeal.price)) {
                bestDeal = new Deal(hotel, currentPrice);
            }
        }
        if (bestDeal != null) {
            return Optional.of(bestDeal);
        } else {
            return Optional.empty();
        }
    }
}

