package com.taxi.app.service;

import com.taxi.app.entity.Booking;
import com.taxi.app.entity.Taxi;

public interface BookingService {
    Iterable<Booking> getBookings();
    Iterable<Booking> getBookings(final Taxi taxi);
    void insertBooking(final Booking booking);
    void assignBooking(final long taxiId, final long bookingId);
    void dropoffBooking(final long taxiId, final long bookingId);
}
