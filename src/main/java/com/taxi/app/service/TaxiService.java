package com.taxi.app.service;

import com.taxi.app.entity.Taxi;

import java.util.Set;

public interface TaxiService {
    Iterable<Taxi> getTaxis();
    Set<Taxi> getTaxisOrderedByBookings();

    Taxi findOneByRegistration(final String registration);

}
