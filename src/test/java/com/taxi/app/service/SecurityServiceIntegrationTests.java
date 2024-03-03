package com.taxi.app.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import com.taxi.app.utils.TestUtils;
import com.taxi.app.TaxiApplication;
import com.taxi.app.utils.TestConstants;
import static org.junit.Assert.assertEquals;
import com.taxi.app.service.security.SecurityService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TaxiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SecurityServiceIntegrationTests {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserDetailsService userDetailsService;

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
    public void testAutologin() {
        securityService.autologin(TestConstants.TAXI151_REGISTRATION, TestConstants.USER_PASSWORD);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(TestConstants.TAXI151_REGISTRATION);
        assertEquals(TestConstants.TAXI151_REGISTRATION, userDetails.getUsername());
    }
}
