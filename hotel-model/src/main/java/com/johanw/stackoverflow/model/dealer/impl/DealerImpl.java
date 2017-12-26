package com.johanw.stackoverflow.model.dealer.impl;

import com.johanw.stackoverflow.model.hotel.CustomerType;
import com.johanw.stackoverflow.model.dealer.Dealer;
import com.johanw.stackoverflow.model.hotel.Hotel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DealerImpl implements Dealer {
    private Hotel[] hotels;

    private DealerImpl() {
    }

    // Pattern: use Optional as described at https://community.oracle.com/docs/DOC-991686
    public Optional<Deal> getCheapest(CustomerType ct, List<LocalDate> dates) {
        if (hotels == null) return Optional.empty();
        if (hotels.length == 0) return Optional.empty();
        Deal bestDeal = null;
        for (Hotel hotel : hotels) {
            double currentPrice = hotel.getPrice(ct, dates);
            if ((bestDeal == null) || (currentPrice < bestDeal.getPrice())) {
                bestDeal = new Deal(hotel, currentPrice);
            }
        }
        if (bestDeal != null) {
            return Optional.of(bestDeal);
        } else {
            return Optional.empty();
        }
    }
    // https://dzone.com/articles/design-patterns-the-builder-pattern
    public static class Builder {
        private Hotel[] hotels;

        Builder() {
        }

        public DealerImpl.Builder withHotels(Hotel[] hotels) {
            this.hotels = hotels;
            return this;
        }

        public Dealer build() {
            DealerImpl di = new DealerImpl();
            di.hotels = hotels;
            return di;
        }

        public static Builder newBuilder() {
            return new Builder();
        }
    }
}

