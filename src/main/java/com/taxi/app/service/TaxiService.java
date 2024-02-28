package com.taxi.app.service;

import com.taxi.app.entity.Taxi;

public interface TaxiService {
    Iterable<Taxi> getTaxis();
    void insertTaxi(final Taxi taxi);
}
