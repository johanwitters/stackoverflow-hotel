package com.johanw.stackoverflow.hotel.model.impl;

import com.johanw.stackoverflow.hotel.model.CustomerType;
import com.johanw.stackoverflow.hotel.model.Hotel;
import com.johanw.stackoverflow.hotel.model.StarRating;

import java.util.List;

public class Price {
    private DayType dayType;
    private CustomerType customerType;

    private Double price;

    public Price(DayType dayType, CustomerType customerType, Double price) {
        this.dayType = dayType;
        this.customerType = customerType;
        this.price = price;
    }

    public DayType getDayType() {
        return dayType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public Double getPrice() {
        return price;
    }

    public static String key(DayType dayType, CustomerType customerType) {
        return dayType.toString() + "-" + customerType.toString();
    }

    public String key() {
        return key(this.dayType, this.customerType);
    }

    // https://dzone.com/articles/design-patterns-the-builder-pattern
    public static class Builder {
        private DayType dayType;
        private CustomerType customerType;
        private Double price;

        Builder() {
        }

        public Builder withDayType(DayType dt) {
            this.dayType = dt;
            return this;
        }

        public Builder withCustomerType(CustomerType ct) {
            this.customerType = ct;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Price build() {
            return new Price(dayType, customerType, price);
        }

        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
