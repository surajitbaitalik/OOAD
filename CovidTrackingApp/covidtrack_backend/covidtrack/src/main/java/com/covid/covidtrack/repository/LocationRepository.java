/**
 * 
 */
package com.covid.covidtrack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.covid.covidtrack.entity.LocationEntity;

/**
 * @author Surajit
 *
 */
@Repository("locationRepository")
public interface LocationRepository extends CrudRepository<LocationEntity, Long> {

}
