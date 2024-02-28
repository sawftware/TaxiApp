package com.taxi.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.taxi.app.entity.Taxi;
import com.taxi.app.entity.Booking;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import com.taxi.app.service.TaxiService;
import com.taxi.app.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private TaxiService taxiService;

    @Autowired
    private BookingService bookingService;

    @GetMapping(value = {"/login"})
    public String getLogin() {
        return "login";
    }

    @GetMapping(value = {"/register"})
    public String getRegister() {
        return "register";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/")
    public String displayIndexPage() {
        return "landing";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/displayTaxis")
    public String displayTaxisPage(final Model model) {
        model.addAttribute("taxis", taxiService.getTaxis());
        return "displayTaxis";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/insertTaxi")
    public String insertTaxiPage(final Model model) {
        model.addAttribute("taxi", new Taxi());

        return "insertTaxi";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/insertTaxi")
    public String insertTaxi(final @ModelAttribute Taxi taxi) {
        taxiService.insertTaxi(taxi);
        return "landing";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/displayBookings")
    public String displayBookingsPage(final Model model) {
        model.addAttribute("bookings", bookingService.getBookings());
        return "displayBookings";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/insertBooking")
    public String insertBookingPage(final Model model) {
        model.addAttribute("booking", new Booking());
        return "insertBooking";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/insertBooking")
    public String insertBooking(final @ModelAttribute Booking booking) {
        bookingService.insertBooking(booking);
        return "landing";
    }

}