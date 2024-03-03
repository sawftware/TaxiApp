package com.taxi.app.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import com.taxi.app.utils.TestUtils;
import com.taxi.app.TaxiApplication;
import com.taxi.app.utils.TestConstants;
import com.taxi.app.entity.security.User;
import static org.junit.Assert.assertEquals;
import com.taxi.app.service.security.UserService;
import com.taxi.app.repository.security.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TaxiApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private UserRepository userRepository;

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
    public void testSaveUser() {
        final User user = User.builder()
                .username(TestConstants.TAXI171_REGISTRATION)
                .password(TestConstants.USER_PASSWORD)
                .passwordConfirm(TestConstants.USER_PASSWORD).build();
        userService.save(user);

        final User result = userRepository.findByUsername(TestConstants.TAXI171_REGISTRATION);

        assertEquals(TestConstants.TAXI171_REGISTRATION, result.getUsername());
        assertEquals(true, result.getTaxi().getIsAvailable());
        assertEquals("Home", result.getTaxi().getLocation().getLocation());
    }
}
