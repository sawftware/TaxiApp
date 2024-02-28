package com.taxi.app.repository.utils;

import com.taxi.app.entity.utils.Location;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> { }