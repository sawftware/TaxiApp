package com.taxi.app.service;

import java.util.Set;
import com.taxi.app.entity.Taxi;

/**
 * TaxiService
 *
 * @author alankavanagh
 *
 * Defines the TaxiService interface
 */
public interface TaxiService {
    Iterable<Taxi> getTaxis();
    Set<Taxi> getTaxisOrderedByBookings();

    Taxi findOneByRegistration(final String registration);

}
