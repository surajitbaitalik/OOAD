package com.covid.covidtrack.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.covid.covidtrack.model.ExternalUser;
import com.covid.covidtrack.service.BluetoothService;



@RestController
public class BluetoothController {
	
	@Autowired
	private BluetoothService bluetoothService;
	
	public static long bluetoothId=0;
	
	@PutMapping(produces = {"application/json"})
	@ResponseBody
	@RequestMapping(value = "/bluetooth", method = RequestMethod.GET)
	public ExternalUser  bluetoothScanner()
	{
		
		if(bluetoothId==11)
		{
			bluetoothId=0;
		}
		return bluetoothService.getBluetoothToken(++bluetoothId);
		
	}

}
