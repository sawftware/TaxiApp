package com.taxi.app.repository;

import java.util.Set;
import com.taxi.app.entity.Taxi;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TaxiRepository extends CrudRepository<Taxi, Long> {
    Taxi findByRegistration(final String registration);
    Set<Taxi> findByOrderByBookingsDesc();
}