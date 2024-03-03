package com.taxi.app.service;

import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import com.taxi.app.entity.Taxi;
import org.junit.jupiter.api.Order;
import com.taxi.app.entity.Booking;
import com.taxi.app.utils.TestUtils;
import com.taxi.app.TaxiApplication;
import com.taxi.app.utils.TestConstants;
import com.taxi.app.entity.utils.Location;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.TestMethodOrder;
import com.taxi.app.repository.TaxiRepository;
import com.taxi.app.repository.BookingRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TaxiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingServiceIntegrationTests {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Before
    public void setUp() {
        testUtils.clearDatabase();

        testUtils.createBookingCenter();
        testUtils.createLocation();
        testUtils.createTaxis();
        testUtils.createUsers();
        testUtils.createBookings();
    }

    @Test
    @Order(1)
    public void testGetBookings() {
        final List<Booking> bookings = (List<Booking>) bookingService.getBookings();
        assertEquals(3, bookings.size());
    }

    @Test
    @Order(2)
    public void testGetBookingsForTaxi() {
        final Taxi taxi = taxiRepository.findByRegistration(TestConstants.TAXI151_REGISTRATION);
        final List<Booking> bookings = (List<Booking>) bookingService.getBookings(taxi);
        assertEquals(taxi.getBookings().size(), bookings.size());
    }

    @Test
    @Order(3)
    public void testInsertBooking() {
        final Location pickup = Location.builder()
                .location(TestConstants.LOCATION_LIBRARY).build();

        final Location dropoff = Location.builder()
                .location(TestConstants.LOCATION_SCHOOL).build();

        final Booking booking = Booking.builder()
                .customer(TestConstants.CUSTOMER_DANNY)
                .pickup(pickup)
                .dropoff(dropoff).build();

        List<Booking> bookings = (List<Booking>) bookingService.getBookings();
        assertEquals(3, bookings.size());

        bookingService.insertBooking(booking);

        bookings = (List<Booking>) bookingService.getBookings();
        assertEquals(4, bookings.size());
    }

    @Test
    @Order(4)
    public void testAssignBooking() {
        final Location pickup = Location.builder()
                .location(TestConstants.LOCATION_LIBRARY).build();

        final Location dropoff = Location.builder()
                .location(TestConstants.LOCATION_SCHOOL).build();

        final Booking booking = Booking.builder()
                .customer(TestConstants.CUSTOMER_DANNY)
                .pickup(pickup)
                .dropoff(dropoff).build();

        List<Booking> bookings = (List<Booking>) bookingService.getBookings();
        assertEquals(3, bookings.size());

        bookingService.insertBooking(booking);

        bookings = (List<Booking>) bookingService.getBookings();
        assertEquals(4, bookings.size());

        final Taxi taxi = taxiRepository.findByRegistration(TestConstants.TAXI151_REGISTRATION);

        final Booking dannyBooking = bookingRepository.findByCustomer(TestConstants.CUSTOMER_DANNY);

        bookings = (List<Booking>) bookingService.getBookings(taxi);
        assertEquals(2, bookings.size());

        bookingService.assignBooking(taxi.getTaxiId(), dannyBooking.getBookingId());

        bookings = (List<Booking>) bookingService.getBookings(taxi);
        assertEquals(3, bookings.size());
    }

    @Test
    @Order(5)
    public void testDropoffBooking() {
        final Location pickup = Location.builder()
                .location(TestConstants.LOCATION_LIBRARY).build();

        final Location dropoff = Location.builder()
                .location(TestConstants.LOCATION_SCHOOL).build();

        final Booking booking = Booking.builder()
                .customer(TestConstants.CUSTOMER_DANNY)
                .pickup(pickup)
                .dropoff(dropoff).build();

        List<Booking> bookings = (List<Booking>) bookingService.getBookings();
        assertEquals(3, bookings.size());

        bookingService.insertBooking(booking);

        bookings = (List<Booking>) bookingService.getBookings();
        assertEquals(4, bookings.size());

        final Taxi taxi = taxiRepository.findByRegistration(TestConstants.TAXI151_REGISTRATION);

        Booking dannyBooking = bookingRepository.findByCustomer(TestConstants.CUSTOMER_DANNY);

        bookings = (List<Booking>) bookingService.getBookings(taxi);
        assertEquals(2, bookings.size());

        bookingService.assignBooking(taxi.getTaxiId(), dannyBooking.getBookingId());

        bookings = (List<Booking>) bookingService.getBookings(taxi);
        assertEquals(3, bookings.size());

        assertNull(dannyBooking.getDropoffDt());
        bookingService.dropoffBooking(taxi.getTaxiId(), dannyBooking.getBookingId());
        dannyBooking = bookingRepository.findByCustomer(TestConstants.CUSTOMER_DANNY);
        assertNotNull(dannyBooking.getDropoffDt());
    }

}
