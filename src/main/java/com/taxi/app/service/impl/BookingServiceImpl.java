package com.taxi.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.taxi.app.entity.Booking;
import com.taxi.app.entity.BookingCenter;
import com.taxi.app.service.BookingService;
import org.springframework.stereotype.Service;
import com.taxi.app.repository.BookingRepository;
import com.taxi.app.repository.BookingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingCenterRepository bookingCenterRepository;

    @Override
    public Iterable<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void insertBooking(final Booking booking) {
        final BookingCenter bookingCenter = bookingCenterRepository.findById(1L).get();

        final Booking newBooking = Booking.builder()
                .customer(booking.getCustomer())
                .bookingCenter(bookingCenter)
                .createDt(new Date()).build();

        bookingRepository.save(newBooking);
    }
}
