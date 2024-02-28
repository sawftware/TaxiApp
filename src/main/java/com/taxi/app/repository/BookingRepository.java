package com.taxi.app.repository;

import com.taxi.app.entity.Booking;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> { }