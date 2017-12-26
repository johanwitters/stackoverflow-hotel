package com.johanw.stackoverflow.model.dealer.test;

import com.johanw.stackoverflow.model.hotel.CustomerType;
import com.johanw.stackoverflow.model.dealer.Dealer;
import com.johanw.stackoverflow.model.dealer.impl.DealerImpl;
import com.johanw.stackoverflow.model.hotel.test.TestHotel;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

// https://www.kenneth-truyers.net/2013/07/15/flexible-and-expressive-unit-tests-with-the-builder-pattern/
public class TestDealer {
    static Dealer dealer = DealerImpl.Builder.newBuilder().withHotels(TestHotel.hotels).build();

    @Test
    public void test() {
        // https://www.programcreek.com/2014/01/create-stream-in-java-8/
        Optional<Dealer.Deal> deal = dealer.getCheapest(CustomerType.REGULAR_CUSTOMER, Arrays.asList(LocalDate.of(2017, 12, 24)));
        Assert.assertTrue(deal.isPresent());
        Assert.assertEquals("hotel_1", deal.get().getHotel().getName());
        Assert.assertEquals(100, deal.get().getPrice(), 0.0);
    }
}
