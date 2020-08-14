/**
 * 
 */
package com.covid.covidtrack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.covidtrack.entity.LocationEntity;
import com.covid.covidtrack.model.LocationInfo;
import com.covid.covidtrack.repository.LocationRepository;

/**
 * @author Surajit
 *
 */
@Service("locationService")
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	

	/* (non-Javadoc)
	 * @see com.covid.covidtrack.service.LocationService#getLocation(long)
	 */
	@Override
	public LocationInfo getLocation(long id) {
		
		 Optional<LocationEntity> opLOc=locationRepository.findById(id);
		 
		 LocationEntity locEntity=opLOc.get();
		 LocationInfo locInfo= new LocationInfo();
		 locInfo.setDensity(locEntity.getDensity());
		 locInfo.setLatitude(locEntity.getLatitude());
		 locInfo.setLongitude(locEntity.getLongitude());
		 locInfo.setSerial_No(locEntity.getSerial_No());
		 return locInfo;
		 
	}

}
