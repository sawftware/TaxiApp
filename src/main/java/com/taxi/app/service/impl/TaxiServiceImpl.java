package com.taxi.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.taxi.app.entity.Taxi;
import com.taxi.app.service.TaxiService;
import com.taxi.app.entity.BookingCenter;
import com.taxi.app.entity.utils.Location;
import org.springframework.stereotype.Service;
import com.taxi.app.repository.TaxiRepository;
import com.taxi.app.repository.utils.LocationRepository;
import com.taxi.app.repository.BookingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaxiServiceImpl implements TaxiService {
    private static final Logger logger = LoggerFactory.getLogger(TaxiServiceImpl.class);

    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private BookingCenterRepository bookingCenterRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Iterable<Taxi> getTaxis() {
        return taxiRepository.findAll();
    }

    @Override
    public void insertTaxi(final Taxi taxi) {
        final Location newLocation = Location.builder()
                .latitude(0)
                .longitude(0).build();
        locationRepository.save(newLocation);

        final BookingCenter bookingCenter = bookingCenterRepository.findById(1L).get();

        final Taxi newTaxi = Taxi.builder()
                .registration(taxi.getRegistration())
                .bookingCenter(bookingCenter)
                .location(newLocation)
                .isAvailable(true).build();

        taxiRepository.save(newTaxi);
    }
}
