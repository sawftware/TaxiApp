package com.taxi.app.service.security.impl;

import java.util.Set;
import org.slf4j.Logger;
import java.util.HashSet;
import org.slf4j.LoggerFactory;
import com.taxi.app.entity.Taxi;
import com.taxi.app.entity.BookingCenter;
import com.taxi.app.entity.security.Role;
import com.taxi.app.entity.security.User;
import com.javadocmd.simplelatlng.LatLng;
import com.taxi.app.entity.utils.Location;
import com.taxi.app.repository.TaxiRepository;
import org.springframework.stereotype.Service;
import com.taxi.app.service.security.UserService;
import com.taxi.app.repository.BookingCenterRepository;
import com.taxi.app.repository.security.RoleRepository;
import com.taxi.app.repository.security.UserRepository;
import com.taxi.app.repository.utils.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * UserServiceImpl
 *
 * @author alankavanagh
 *
 * Implementation of the UserService interface
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BookingCenterRepository bookingCenterRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(final User user) {
        logger.debug("UserServiceImpl: Executing save()");

        logger.debug("UserServiceImpl: Saving user: " + user);
        final Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOneByName("ROLE_USER"));

        final BookingCenter bookingCenter = bookingCenterRepository.findById(1L).get();

        final Location location = Location.builder()
                .location("Home")
                .latitude(LatLng.random().getLatitude())
                .longitude(LatLng.random().getLongitude()).build();
        locationRepository.save(location);

        final Taxi taxi = Taxi.builder()
                .registration(user.getUsername())
                .bookingCenter(bookingCenter)
                .isAvailable(true)
                .location(location)
                .build();
        taxiRepository.save(taxi);

        final User newUser = User.builder()
                .username(user.getUsername())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .taxi(taxi)
                .roles(new HashSet<>(roles)).build();
        userRepository.save(newUser);
    }
}
