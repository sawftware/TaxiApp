package com.taxi.app.service;

import com.taxi.app.entity.Booking;

public interface BookingService {
    Iterable<Booking> getBookings();
    void insertBooking(final Booking booking);
}
