package com.taxi.app.repository.utils;

import com.taxi.app.entity.utils.Location;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * LocationRepository
 *
 * @author alankavanagh
 *
 * Location Repository used for database management of the Location entity
 */
@Repository
public interface LocationRepository extends CrudRepository<Location, Long> { }