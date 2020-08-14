/**
 * 
 */
package com.covid.covidtrack.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.covidtrack.model.LocationInfo;
import com.covid.covidtrack.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Surajit
 *
 */
@RestController
public class LocationController {
	
	public static long locationId=0;
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "/locationInfo", method = RequestMethod.GET)
	public String getLocation() throws JsonProcessingException
	{
		if(locationId==13)
		{
			locationId=0;
		}
		LocationInfo loc=locationService.getLocation(++locationId);
		Map<String,String> locMap=new HashMap<>();
		locMap.put("location", loc.getLatitude()+", "+loc.getLongitude());
		locMap.put("density", loc.getDensity()+"");
		ObjectMapper objM=new ObjectMapper();
		
		//before sending the location to app store it to the database
		
		
		return objM.writeValueAsString(locMap);
		
	}
	

}
