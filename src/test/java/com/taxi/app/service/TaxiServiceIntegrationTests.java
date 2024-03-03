package com.taxi.app.service;

import java.util.Set;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import com.taxi.app.entity.Taxi;
import com.taxi.app.TaxiApplication;
import com.taxi.app.utils.TestUtils;
import com.taxi.app.utils.TestConstants;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TaxiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TaxiServiceIntegrationTests {

    @Autowired
    private TaxiService taxiService;

    @Autowired
    private TestUtils testUtils;

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
    public void testGetTaxis() {
        final List<Taxi> taxis = (List<Taxi>) taxiService.getTaxis();
        assertEquals(2, taxis.size());
    }

    @Test
    public void testFindByRegistration() {
        final Taxi taxi151 = taxiService.getTaxiByRegistration(TestConstants.TAXI151_REGISTRATION);
        assertEquals(TestConstants.TAXI151_REGISTRATION, taxi151.getRegistration());

        final Taxi taxi161 = taxiService.getTaxiByRegistration(TestConstants.TAXI161_REGISTRATION);
        assertEquals(TestConstants.TAXI161_REGISTRATION, taxi161.getRegistration());
    }

    @Test
    public void testGetTaxisOrderedByBookingsDesc() {
        final Set<Taxi> taxis = (Set<Taxi>) taxiService.getTaxisOrderedByBookingsDesc();

        int mostBookings = Integer.MIN_VALUE;

        for(final Taxi taxi: taxis) {
            assertTrue(taxi.getBookings().size() > mostBookings);
            mostBookings = taxi.getBookings().size();
        }
    }
}
