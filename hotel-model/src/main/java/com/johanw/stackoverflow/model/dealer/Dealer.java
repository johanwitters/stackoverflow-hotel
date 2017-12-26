package com.johanw.stackoverflow.model.dealer;

import com.johanw.stackoverflow.model.hotel.CustomerType;
import com.johanw.stackoverflow.model.hotel.Hotel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Dealer {
    public class Deal {
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

    public Optional<Deal> getCheapest(CustomerType ct, List<LocalDate> dates);
}

