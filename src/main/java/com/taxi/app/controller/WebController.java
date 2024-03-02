package com.taxi.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.Principal;
import com.taxi.app.entity.Taxi;
import com.taxi.app.entity.Booking;
import org.springframework.ui.Model;
import com.taxi.app.service.TaxiService;
import com.taxi.app.entity.security.User;
import com.taxi.app.service.BookingService;
import org.springframework.stereotype.Controller;
import com.taxi.app.service.security.UserService;
import com.taxi.app.service.security.SecurityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * WebController
 *
 * @author alankavanagh
 *
 * WebController that defines the HTTP URL Endpoints for the application
 */
@Controller
public class WebController {
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TaxiService taxiService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SecurityService securityService;

    @GetMapping(value = {"/login"})
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(final @ModelAttribute("userRegisterForm") User user) {
        logger.debug("WebController: POST /login");

        securityService.autologin(user.getUsername(), user.getPassword());
        return "redirect:/";
    }

    @GetMapping(value = {"/register"})
    public String getRegister(final Model model) {
        logger.debug("WebController: GET /register");

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(final @ModelAttribute("userForm") User user) {
        logger.debug("WebController: POST /register");

        userService.save(user);
        securityService.autologin(user.getUsername(), user.getPassword());
        return "redirect:/";
    }

    @GetMapping("/")
    public String displayIndex(final Model model, final Principal principal) {
        logger.debug("WebController: GET /");

        final Taxi usersTaxi = taxiService.findOneByRegistration(principal.getName());
        model.addAttribute("taxi", usersTaxi);
        model.addAttribute("taxiBookings", bookingService.getBookings(usersTaxi));
        model.addAttribute("bookings", bookingService.getBookings());
        model.addAttribute("taxis", taxiService.getTaxisOrderedByBookings());
        return "landing";
    }

    @GetMapping("/displayTaxis")
    public String displayTaxis(final Model model) {
        logger.debug("WebController: GET /displayTaxis");

        model.addAttribute("taxis", taxiService.getTaxis());
        return "displayTaxis";
    }

    @GetMapping("/displayBookings")
    public String displayBookings(final Model model, final Principal principal) {
        logger.debug("WebController: GET /displayBookings");

        model.addAttribute("bookings", bookingService.getBookings());
        model.addAttribute("taxi", taxiService.findOneByRegistration(principal.getName()));
        return "displayBookings";
    }

    @PostMapping("/assignBooking")
    public String assignBooking(final @ModelAttribute("bookingId") long bookingId, final Principal principal) {
        logger.debug("WebController: POST /assignBooking");

        bookingService.assignBooking(
                taxiService.findOneByRegistration(principal.getName()).getTaxiId(), bookingId);

        return "redirect:/";
    }

    @PostMapping("/dropoffBooking")
    public String dropoffBooking(final @ModelAttribute("bookingId") long bookingId, final Principal principal) {
        logger.debug("WebController: POST /dropoffBooking");

        bookingService.dropoffBooking(
                taxiService.findOneByRegistration(principal.getName()).getTaxiId(), bookingId);

        return "redirect:/";
    }

    @GetMapping("/insertBooking")
    public String insertBooking(final Model model) {
        logger.debug("WebController: GET /insertBooking");

        model.addAttribute("booking", new Booking());
        return "insertBooking";
    }

    @PostMapping("/insertBooking")
    public String insertBooking(final @ModelAttribute Booking booking) {
        logger.debug("WebController: POST /insertBooking");

        bookingService.insertBooking(booking);
        return "redirect:/displayBookings";
    }
}