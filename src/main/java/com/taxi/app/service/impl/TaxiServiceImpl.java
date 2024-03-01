package com.taxi.app.service.impl;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.taxi.app.entity.Taxi;
import com.taxi.app.service.TaxiService;
import com.taxi.app.repository.TaxiRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TaxiServiceImpl
 *
 * @author alankavanagh
 *
 * Implementation of the TaxiService interface
 */
@Service
public class TaxiServiceImpl implements TaxiService {
    private static final Logger logger = LoggerFactory.getLogger(TaxiServiceImpl.class);

    @Autowired
    private TaxiRepository taxiRepository;

    @Override
    public Iterable<Taxi> getTaxis() {
        logger.debug("TaxiServiceImpl: Executing getTaxis()");

        return taxiRepository.findAll();
    }

    @Override
    public Set<Taxi> getTaxisOrderedByBookings() {
        logger.debug("TaxiServiceImpl: Executing getTaxisOrderedByBookings()");

        return taxiRepository.findByOrderByBookingsDesc();
    }

    @Override
    public Taxi findOneByRegistration(final String registration) {
        logger.debug("TaxiServiceImpl: Executing findOneByRegistration()");

        return taxiRepository.findByRegistration(registration);
    }
}
