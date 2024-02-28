package com.taxi.app.repository;

import com.taxi.app.entity.BookingCenter;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface BookingCenterRepository extends CrudRepository<BookingCenter, Long> { }