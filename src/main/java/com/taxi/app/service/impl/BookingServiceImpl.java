package com.taxi.app.service.impl;

import java.util.Date;

import com.taxi.app.entity.Taxi;
import com.taxi.app.repository.TaxiRepository;
import com.taxi.app.service.TaxiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.taxi.app.entity.Booking;
import com.taxi.app.entity.BookingCenter;
import com.taxi.app.service.BookingService;
import org.springframework.stereotype.Service;
import com.taxi.app.repository.BookingRepository;
import com.taxi.app.repository.BookingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingCenterRepository bookingCenterRepository;

    @Override
    public Iterable<Booking> getBookings() {
        logger.debug("BookingServiceImpl: Executing getBookings()");

        return bookingRepository.findAll();
    }

    @Override
    public Iterable<Booking> getBookings(final Taxi taxi) {
        logger.debug("BookingServiceImpl: Executing getBookings(taxi)");

        return bookingRepository.findAllByTaxi(taxi);
    }

    @Override
    public void insertBooking(final Booking booking) {
        logger.debug("BookingServiceImpl: Executing insertBooking()");

        final BookingCenter bookingCenter = bookingCenterRepository.findById(1L).get();
        logger.debug("BookingServiceImpl: Booking center found: " + bookingCenter);

        final Booking newBooking = Booking.builder()
                .customer(booking.getCustomer())
                .bookingCenter(bookingCenter)
                .createDt(new Date()).build();

        logger.debug("BookingServiceImpl: Booking created, persisting");
        bookingRepository.save(newBooking);
    }

    @Override
    public void assignBooking(final long taxiId, final long bookingId) {
        logger.debug("BookingServiceImpl: Executing assignBooking()");

        final Taxi taxi = taxiRepository.findById(taxiId).get();
        logger.debug("BookingServiceImpl: Taxi found: " + taxi);

        final Booking booking = bookingRepository.findById(bookingId).get();
        logger.debug("BookingServiceImpl: Booking found: " + booking);
        booking.setTaxi(taxi);
        booking.setAssignedDt(new Date());

        logger.debug("BookingServiceImpl: Booking assigned, persisting");
        bookingRepository.save(booking);

        logger.debug("BookingServiceImpl: Setting taxi status to booked, persisting");
        taxi.setIsAvailable(false);
        taxiRepository.save(taxi);
    }

    @Override
    public void dropoffBooking(final long taxiId, final long bookingId) {
        logger.debug("BookingServiceImpl: Executing dropoffBooking()");

        final Taxi taxi = taxiRepository.findById(taxiId).get();
        logger.debug("BookingServiceImpl: Taxi found: " + taxi);

        final Booking booking = bookingRepository.findById(bookingId).get();
        logger.debug("BookingServiceImpl: Booking found: " + booking);
        booking.setDropoffDt(new Date());

        logger.debug("BookingServiceImpl: Booking dropoff complete, persisting");
        bookingRepository.save(booking);

        logger.debug("BookingServiceImpl: Setting taxi status to available, persisting");
        taxi.setIsAvailable(true);
        taxiRepository.save(taxi);
    }
}
