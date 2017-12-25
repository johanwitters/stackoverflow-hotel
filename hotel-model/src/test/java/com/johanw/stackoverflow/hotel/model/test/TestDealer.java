package com.johanw.stackoverflow.hotel.model.test;

import com.johanw.stackoverflow.hotel.model.CustomerType;
import com.johanw.stackoverflow.hotel.model.Dealer;
import com.johanw.stackoverflow.hotel.model.Hotel;
import com.johanw.stackoverflow.hotel.model.StarRating;
import com.johanw.stackoverflow.hotel.model.impl.DayType;
import com.johanw.stackoverflow.hotel.model.impl.HotelImpl;
import com.johanw.stackoverflow.hotel.model.impl.Price;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

// https://www.kenneth-truyers.net/2013/07/15/flexible-and-expressive-unit-tests-with-the-builder-pattern/
public class TestDealer {
    static Dealer dealer = new Dealer(TestHotel.hotels);

    @Test
    public void test() {
        Optional<Dealer.Deal> deal = dealer.getCheapest(CustomerType.REGULAR_CUSTOMER, LocalDate.of(2017, 12, 24));
        Assert.assertTrue(deal.isPresent());
        Assert.assertEquals("hotel_1", deal.get().getHotel().getName());
        Assert.assertEquals(100, deal.get().getPrice(), 0.0);
    }
}
