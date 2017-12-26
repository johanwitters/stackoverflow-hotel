package com.johanw.stackoverflow.model.hotel.test;

import com.johanw.stackoverflow.model.hotel.CustomerType;
import com.johanw.stackoverflow.model.hotel.Hotel;
import com.johanw.stackoverflow.model.hotel.StarRating;
import com.johanw.stackoverflow.model.hotel.impl.DayType;
import com.johanw.stackoverflow.model.hotel.impl.HotelImpl;
import com.johanw.stackoverflow.model.hotel.impl.Price;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

// https://www.kenneth-truyers.net/2013/07/15/flexible-and-expressive-unit-tests-with-the-builder-pattern/
public class TestHotel {
    public static Hotel[] hotels = new Hotel[] {
            HotelImpl.Builder.newBuilder("hotel_1").
                    withPrices(
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKEND).
                                    withCustomerType(CustomerType.REGULAR_CUSTOMER).
                                    withPrice(100.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKEND).
                                    withCustomerType(CustomerType.PRIVILEGE_CUSTOMER).
                                    withPrice(90.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKDAY).
                                    withCustomerType(CustomerType.REGULAR_CUSTOMER).
                                    withPrice(80.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKDAY).
                                    withCustomerType(CustomerType.PRIVILEGE_CUSTOMER).
                                    withPrice(70.0).
                                    build()
                    ).
                    withRating(StarRating.FIVE_STARS).build(),
            HotelImpl.Builder.newBuilder("hotel_2").
                    withPrices(
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKEND).
                                    withCustomerType(CustomerType.REGULAR_CUSTOMER).
                                    withPrice(120.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKEND).
                                    withCustomerType(CustomerType.PRIVILEGE_CUSTOMER).
                                    withPrice(100.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKDAY).
                                    withCustomerType(CustomerType.REGULAR_CUSTOMER).
                                    withPrice(70.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKDAY).
                                    withCustomerType(CustomerType.PRIVILEGE_CUSTOMER).
                                    withPrice(50.0).
                                    build()
                    ).
                    withRating(StarRating.FIVE_STARS).build(),
            HotelImpl.Builder.newBuilder("hotel_3").
                    withPrices(
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKEND).
                                    withCustomerType(CustomerType.REGULAR_CUSTOMER).
                                    withPrice(150.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKEND).
                                    withCustomerType(CustomerType.PRIVILEGE_CUSTOMER).
                                    withPrice(120.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKDAY).
                                    withCustomerType(CustomerType.REGULAR_CUSTOMER).
                                    withPrice(120.0).
                                    build(),
                            Price.Builder.newBuilder().
                                    withDayType(DayType.WEEKDAY).
                                    withCustomerType(CustomerType.PRIVILEGE_CUSTOMER).
                                    withPrice(80.0).
                                    build()
                    ).
                    withRating(StarRating.FIVE_STARS).build()
    };

    @Test
    public void test() {
        Hotel hotel1 = hotels[0];
        double price1 = hotel1.getPrice(CustomerType.REGULAR_CUSTOMER, Arrays.asList(LocalDate.of(2017, 12, 24)));
        Assert.assertEquals(100, price1, 0.0);
        double price2 = hotel1.getPrice(CustomerType.PRIVILEGE_CUSTOMER, Arrays.asList((LocalDate.of(2017, 12, 24))));
        Assert.assertEquals(90, price2, 0.0);
        double price3 = hotel1.getPrice(CustomerType.REGULAR_CUSTOMER, Arrays.asList((LocalDate.of(2017, 12, 25))));
        Assert.assertEquals(80, price3, 0.0);
        double price4 = hotel1.getPrice(CustomerType.PRIVILEGE_CUSTOMER, Arrays.asList((LocalDate.of(2017, 12, 25))));
        Assert.assertEquals(70, price4, 0.0);
    }
}
