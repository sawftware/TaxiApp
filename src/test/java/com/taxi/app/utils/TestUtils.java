package com.taxi.app.utils;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import com.taxi.app.entity.Taxi;
import com.taxi.app.entity.Booking;
import com.javadocmd.simplelatlng.LatLng;
import com.taxi.app.entity.BookingCenter;
import com.taxi.app.entity.security.Role;
import com.taxi.app.entity.security.User;
import com.taxi.app.entity.utils.Location;
import com.taxi.app.repository.TaxiRepository;
import org.springframework.stereotype.Component;
import com.taxi.app.repository.BookingRepository;
import com.taxi.app.repository.BookingCenterRepository;
import com.taxi.app.repository.security.RoleRepository;
import com.taxi.app.repository.security.UserRepository;
import com.taxi.app.repository.utils.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class TestUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BookingCenterRepository bookingCenterRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createBookingCenter() {
        final BookingCenter bookingCenter = BookingCenter.builder()
                .name(TestConstants.BOOKING_CENTER_NAME).build();
        bookingCenterRepository.save(bookingCenter);
    }

    public void createLocation() {
        final Location location = Location.builder()
                .location(TestConstants.LOCATION_NAME)
                .latitude(LatLng.random().getLatitude())
                .longitude(LatLng.random().getLongitude()).build();
        locationRepository.save(location);
    }

    public void createTaxis() {
        final BookingCenter bookingCenter =
                bookingCenterRepository.findByName(TestConstants.BOOKING_CENTER_NAME);

        final Location location =
                locationRepository.findByLocation(TestConstants.LOCATION_NAME);

        final Taxi taxi151 = Taxi.builder()
                .registration(TestConstants.TAXI151_REGISTRATION)
                .isAvailable(true)
                .bookingCenter(bookingCenter)
                .location(location).build();
        taxiRepository.save(taxi151);

        final Taxi taxi161 = Taxi.builder()
                .registration(TestConstants.TAXI161_REGISTRATION)
                .isAvailable(true)
                .bookingCenter(bookingCenter)
                .location(location).build();
        taxiRepository.save(taxi161);
    }

    public void createUsers() {
        final Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(TestConstants.USER_ROLE_NAME));

        final Taxi taxi151 = taxiRepository.findByRegistration(TestConstants.TAXI151_REGISTRATION);
        final Taxi taxi161 = taxiRepository.findByRegistration(TestConstants.TAXI161_REGISTRATION);

        final User user151 = User.builder()
                .username(taxi151.getRegistration())
                .password(bCryptPasswordEncoder.encode(TestConstants.USER_PASSWORD))
                .taxi(taxi151)
                .roles(new HashSet<>(roles)).build();
        userRepository.save(user151);

        final User user161 = User.builder()
                .username(taxi161.getRegistration())
                .password(bCryptPasswordEncoder.encode(TestConstants.USER_PASSWORD))
                .taxi(taxi161)
                .roles(new HashSet<>(roles)).build();
        userRepository.save(user161);
    }

    public void createBookings() {
        final BookingCenter bookingCenter =
                bookingCenterRepository.findByName(TestConstants.BOOKING_CENTER_NAME);

        final Location location =
                locationRepository.findByLocation(TestConstants.LOCATION_NAME);

        final Taxi taxi151 =
                taxiRepository.findByRegistration(TestConstants.TAXI151_REGISTRATION);

        final Taxi taxi161 =
                taxiRepository.findByRegistration(TestConstants.TAXI161_REGISTRATION);

        final Booking annieBooking = Booking.builder()
                .customer(TestConstants.CUSTOMER_ANNIE)
                .bookingCenter(bookingCenter)
                .pickup(location)
                .taxi(taxi151)
                .dropoff(location)
                .createDt(new Date())
                .assignedDt(new Date())
                .dropoffDt(new Date()).build();
        bookingRepository.save(annieBooking);

        final Booking barryBooking = Booking.builder()
                .customer(TestConstants.CUSTOMER_BARRY)
                .bookingCenter(bookingCenter)
                .pickup(location)
                .taxi(taxi151)
                .dropoff(location)
                .createDt(new Date())
                .assignedDt(new Date())
                .dropoffDt(new Date()).build();
        bookingRepository.save(barryBooking);

        final Booking charlieBooking = Booking.builder()
                .customer(TestConstants.CUSTOMER_CHARLIE)
                .bookingCenter(bookingCenter)
                .pickup(location)
                .taxi(taxi161)
                .dropoff(location)
                .createDt(new Date())
                .assignedDt(new Date())
                .dropoffDt(new Date()).build();
        bookingRepository.save(charlieBooking);
    }

    public void clearDatabase() {
        bookingRepository.deleteAll();
        userRepository.deleteAll();
        taxiRepository.deleteAll();
        locationRepository.deleteAll();
        bookingCenterRepository.deleteAll();
    }
}
