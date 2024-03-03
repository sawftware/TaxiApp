package com.taxi.app.repository;

import com.taxi.app.entity.BookingCenter;
import com.taxi.app.entity.security.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * BookingCenterRepository
 *
 * @author alankavanagh
 *
 * Booking Center Repository used for database management of the BookingCenter entity
 */
@Repository
public interface BookingCenterRepository extends CrudRepository<BookingCenter, Long> {
    BookingCenter findByName(final String name);
}