package com.covid.covidtrack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid.covidtrack.entity.BluetoothUserInfoEntity;
import com.covid.covidtrack.model.ExternalUser;
import com.covid.covidtrack.repository.BluetoothRepository;

@Service("bluetoothService")
public class BluetoothServiceImpl implements BluetoothService {
	
	@Autowired
	private BluetoothRepository bluetoothRepository;

	@Override
	public ExternalUser getBluetoothToken(long id) {
		 Optional<BluetoothUserInfoEntity> opBlu=bluetoothRepository.findById(id);
		 BluetoothUserInfoEntity be=opBlu.get(); 
		 ExternalUser bUser=new ExternalUser();
		 bUser.setToken_id(be.getToken_id());
		 bUser.setUserdiseaseStatus(be.getUserdiseaseStatus());
		 bUser.setUserLatitude(be.getUserLatitude());
		 bUser.setUserLongitude(be.getUserLongitude());
		 bUser.setAge(be.getAge());
		 bUser.setDistance(be.getDistance());
		 //if the status is not normal, then store the location and received status in database
		 if(!bUser.getUserdiseaseStatus().equalsIgnoreCase("normal"))
		 {
			 bUser=analyzeToken(bUser);
		 }
		return bUser;
	}

	@Override
	public ExternalUser analyzeToken(ExternalUser bUser) {
		//if status is infected and distance is <=5 then set warning flag true
		//or else if receive status as  symptoms then set warning flag as true
		//else if infected person is within 2m then set the alarm service on and update to CDC
		//every time it will save the data
		
		if(bUser.getDistance()<=2 && bUser.getUserdiseaseStatus().equalsIgnoreCase("infected"))
		{
			bUser.setAlarm(true);
			//update cdc
		}else if((bUser.getDistance()<=5 && bUser.getUserdiseaseStatus().equalsIgnoreCase("infected"))||bUser.getUserdiseaseStatus().equalsIgnoreCase("symptoms"))
		{
			bUser.setWarning(true);
		}else {
			//save or update user_location_info table
		}
		
		
		return bUser;
	}


	
	

}
