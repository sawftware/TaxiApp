package com.taxi.app.repository;

import java.util.Set;
import com.taxi.app.entity.Taxi;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * TaxiRepository
 *
 * @author alankavanagh
 *
 * Taxi Repository used for database management of the Taxi entity
 */
@Repository
public interface TaxiRepository extends CrudRepository<Taxi, Long> {
    Taxi findByRegistration(final String registration);
    Set<Taxi> findByOrderByBookingsDesc();
}