package com.taxi.app.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import com.taxi.app.TaxiApplication;
import com.taxi.app.utils.TestUtils;
import com.taxi.app.utils.TestConstants;
import com.taxi.app.entity.security.User;
import org.springframework.http.MediaType;
import static org.junit.Assert.assertNotNull;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import com.taxi.app.repository.security.UserRepository;
import static org.hamcrest.CoreMatchers.containsString;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.context.support.WithAnonymousUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TaxiApplication.class)
@AutoConfigureMockMvc
public class WebControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

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
    @WithAnonymousUser
    public void whenGetLoginWithoutAuth_thenStatus200() throws Exception {
        mvc.perform(
                get("/login")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Please Sign In")));
    }

    @Test
    @WithAnonymousUser
    public void whenGetRegisterWithoutAuth_thenStatus200() throws Exception {
        mvc.perform(
                get("/register")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Register a Taxi")));
    }

    @Test
    @WithMockUser(username=TestConstants.ADMIN,roles={"ADMIN"})
    public void whenPostInsertBooking_thenStatus200() throws Exception {
        mvc.perform(post("/insertBooking")
                        .param("customer", TestConstants.CUSTOMER_DANNY)
                        .param("pickup.location", TestConstants.LOCATION_SCHOOL)
                        .param("dropoff.location", TestConstants.LOCATION_LIBRARY)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/displayBookings"));
    }

    @Test
    @WithAnonymousUser
    public void whenPostRegisterWithoutAuth_thenStatus200() throws Exception {
        mvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("username", TestConstants.TAXI171_REGISTRATION)
                        .param("password", TestConstants.USER_PASSWORD)
                        .param("passwordConfirm", TestConstants.USER_PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        final User newUser =
                userRepository.findByUsername(TestConstants.TAXI171_REGISTRATION);
        assertNotNull(newUser);
    }

    @Test
    @WithMockUser(username=TestConstants.ADMIN,roles={"ADMIN"})
    public void whenGetInsertBookingAsAdmin_thenStatus200() throws Exception {
        mvc.perform(
                get("/insertBooking")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Insert Booking")));
    }

    @Test
    @WithMockUser(username=TestConstants.ADMIN,roles={"ADMIN"})
    public void whenGetDisplayTaxisAsAdmin_thenStatus200() throws Exception {
        mvc.perform(
                get("/displayTaxis")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Display Taxis")));
    }

    @Test
    @WithMockUser(username=TestConstants.USER)
    public void whenGetDisplayBookingsAsUser_thenStatus200() throws Exception {
        mvc.perform(
                get("/displayBookings")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Display Bookings")));
    }

    @Test
    @WithMockUser(username=TestConstants.ADMIN,roles={"ADMIN"})
    public void whenGetDisplayBookingsAsAdmin_thenStatus200() throws Exception {
        mvc.perform(
                get("/displayBookings")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Display Bookings")));
    }


    @Test
    @WithMockUser(username=TestConstants.ADMIN,roles={"ADMIN"})
    public void whenGetLandingAsAdmin_thenStatus200() throws Exception {
        mvc.perform(
                get("/")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Bookings Statistics")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
