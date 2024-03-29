package com.taxi.app.repository;

import java.util.List;
import com.taxi.app.entity.Taxi;
import com.taxi.app.entity.Booking;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * BookingRepository
 *
 * @author alankavanagh
 *
 * Booking Repository used for database management of the Booking entity
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAllByTaxi(final Taxi taxi);
    Booking findByCustomer(final String customer);
}