/**
 * 
 */
package com.covid.covidtrack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid.covidtrack.model.LocationDiseaseInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * @author Surajit
 *
 */
@RestController
public class CDCController {
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/diseaseInfo", method = RequestMethod.POST)
	public String getDiseasInfo(@RequestBody String position) throws JsonMappingException, JsonProcessingException
	{
		System.out.println(position);
		Map<String, Integer> map = new ObjectMapper().readValue(position, Map.class);
		String result=null;
		Map<String,ArrayList<LocationDiseaseInfo>> hm1=new HashMap<>();
		ArrayList<LocationDiseaseInfo> a1=new ArrayList<>();
		a1.add(new LocationDiseaseInfo("13-Jul","100"));
		a1.add(new LocationDiseaseInfo("14-Jul","200"));
		a1.add(new LocationDiseaseInfo("15-Jul","300"));
		a1.add(new LocationDiseaseInfo("16-Jul","400"));
		a1.add(new LocationDiseaseInfo("17-Jul","500"));
		a1.add(new LocationDiseaseInfo("18-Jul","600"));
		a1.add(new LocationDiseaseInfo("19-Jul","700"));
		a1.add(new LocationDiseaseInfo("20-Jul","800"));
		a1.add(new LocationDiseaseInfo("21-Jul","900"));
		a1.add(new LocationDiseaseInfo("22-Jul","1000"));
		
		hm1.put("newcases", a1);
		ArrayList<LocationDiseaseInfo> a2=new ArrayList<>();
		a2.add(new LocationDiseaseInfo("13-Jul","80"));
		a2.add(new LocationDiseaseInfo("14-Jul","180"));
		a2.add(new LocationDiseaseInfo("15-Jul","280"));
		a2.add(new LocationDiseaseInfo("16-Jul","380"));
		a2.add(new LocationDiseaseInfo("17-Jul","480"));
		a2.add(new LocationDiseaseInfo("18-Jul","580"));
		a2.add(new LocationDiseaseInfo("19-Jul","680"));
		a2.add(new LocationDiseaseInfo("20-Jul","780"));
		a2.add(new LocationDiseaseInfo("21-Jul","880"));
		a2.add(new LocationDiseaseInfo("22-Jul","980"));
		hm1.put("recoveries", a2);
		ArrayList<LocationDiseaseInfo> a3=new ArrayList<>();
		a3.add(new LocationDiseaseInfo("13-Jul","10"));
		a3.add(new LocationDiseaseInfo("14-Jul","20"));
		a3.add(new LocationDiseaseInfo("15-Jul","3"));
		a3.add(new LocationDiseaseInfo("16-Jul","40"));
		a3.add(new LocationDiseaseInfo("17-Jul","5"));
		a3.add(new LocationDiseaseInfo("18-Jul","50"));
		a3.add(new LocationDiseaseInfo("19-Jul","10"));
		a3.add(new LocationDiseaseInfo("20-Jul","20"));
		a3.add(new LocationDiseaseInfo("21-Jul","15"));
		a3.add(new LocationDiseaseInfo("22-Jul","25"));
		hm1.put("daily deaths", a3);
		 JSONObject json1 = new JSONObject(hm1);
		 if(map.get("position")==0)
		 {
			 result= json1.toString(); 
			 return result;
		 }
		 
		 // for the position 1
		 Map<String,ArrayList<LocationDiseaseInfo>> hm2=new HashMap<>();
		 ArrayList<LocationDiseaseInfo> a4=new ArrayList<>();
			a4.add(new LocationDiseaseInfo("13-Jul","1000"));
			a4.add(new LocationDiseaseInfo("14-Jul","900"));
			a4.add(new LocationDiseaseInfo("15-Jul","800"));
			a4.add(new LocationDiseaseInfo("16-Jul","700"));
			a4.add(new LocationDiseaseInfo("17-Jul","600"));
			a4.add(new LocationDiseaseInfo("18-Jul","500"));
			a4.add(new LocationDiseaseInfo("19-Jul","400"));
			a4.add(new LocationDiseaseInfo("20-Jul","300"));
			a4.add(new LocationDiseaseInfo("21-Jul","200"));
			a4.add(new LocationDiseaseInfo("22-Jul","100"));
			
			hm2.put("newcases", a4);
			ArrayList<LocationDiseaseInfo> a5=new ArrayList<>();
			a5.add(new LocationDiseaseInfo("13-Jul","80"));
			a5.add(new LocationDiseaseInfo("14-Jul","180"));
			a5.add(new LocationDiseaseInfo("15-Jul","280"));
			a5.add(new LocationDiseaseInfo("16-Jul","380"));
			a5.add(new LocationDiseaseInfo("17-Jul","480"));
			a5.add(new LocationDiseaseInfo("18-Jul","580"));
			a5.add(new LocationDiseaseInfo("19-Jul","680"));
			a5.add(new LocationDiseaseInfo("20-Jul","780"));
			a5.add(new LocationDiseaseInfo("21-Jul","880"));
			a5.add(new LocationDiseaseInfo("22-Jul","980"));
			hm2.put("recoveries", a5);
			ArrayList<LocationDiseaseInfo> a6=new ArrayList<>();
			a6.add(new LocationDiseaseInfo("13-Jul","20"));
			a6.add(new LocationDiseaseInfo("14-Jul","25"));
			a6.add(new LocationDiseaseInfo("15-Jul","30"));
			a6.add(new LocationDiseaseInfo("16-Jul","40"));
			a6.add(new LocationDiseaseInfo("17-Jul","5"));
			a6.add(new LocationDiseaseInfo("18-Jul","50"));
			a6.add(new LocationDiseaseInfo("19-Jul","10"));
			a6.add(new LocationDiseaseInfo("20-Jul","20"));
			a6.add(new LocationDiseaseInfo("21-Jul","15"));
			a6.add(new LocationDiseaseInfo("22-Jul","25"));
			hm2.put("daily deaths", a6);
			 JSONObject json2 = new JSONObject(hm2);
			 if(map.get("position")==1)
			 {
				 result= json2.toString();
				 return result;
			 }
		 
		 //for position 2
		 
		 
			 Map<String,ArrayList<LocationDiseaseInfo>> hm3=new HashMap<>();
			 ArrayList<LocationDiseaseInfo> a7=new ArrayList<>();
				a7.add(new LocationDiseaseInfo("13-Jul","100"));
				a7.add(new LocationDiseaseInfo("14-Jul","200"));
				a7.add(new LocationDiseaseInfo("15-Jul","300"));
				a7.add(new LocationDiseaseInfo("16-Jul","400"));
				a7.add(new LocationDiseaseInfo("17-Jul","500"));
				a7.add(new LocationDiseaseInfo("18-Jul","600"));
				a7.add(new LocationDiseaseInfo("19-Jul","700"));
				a7.add(new LocationDiseaseInfo("20-Jul","800"));
				a7.add(new LocationDiseaseInfo("21-Jul","900"));
				a7.add(new LocationDiseaseInfo("22-Jul","1000"));
				
				hm3.put("newcases", a7);
				ArrayList<LocationDiseaseInfo> a8=new ArrayList<>();
				a8.add(new LocationDiseaseInfo("13-Jul","80"));
				a8.add(new LocationDiseaseInfo("14-Jul","180"));
				a8.add(new LocationDiseaseInfo("15-Jul","280"));
				a8.add(new LocationDiseaseInfo("16-Jul","380"));
				a8.add(new LocationDiseaseInfo("17-Jul","480"));
				a8.add(new LocationDiseaseInfo("18-Jul","580"));
				a8.add(new LocationDiseaseInfo("19-Jul","680"));
				a8.add(new LocationDiseaseInfo("20-Jul","780"));
				a8.add(new LocationDiseaseInfo("21-Jul","880"));
				a8.add(new LocationDiseaseInfo("22-Jul","980"));
				hm3.put("recoveries", a8);
				ArrayList<LocationDiseaseInfo> a9=new ArrayList<>();
				a9.add(new LocationDiseaseInfo("13-Jul","20"));
				a9.add(new LocationDiseaseInfo("14-Jul","25"));
				a9.add(new LocationDiseaseInfo("15-Jul","30"));
				a9.add(new LocationDiseaseInfo("16-Jul","40"));
				a9.add(new LocationDiseaseInfo("17-Jul","5"));
				a9.add(new LocationDiseaseInfo("18-Jul","50"));
				a9.add(new LocationDiseaseInfo("19-Jul","10"));
				a9.add(new LocationDiseaseInfo("20-Jul","20"));
				a9.add(new LocationDiseaseInfo("21-Jul","15"));
				a9.add(new LocationDiseaseInfo("22-Jul","25"));
				hm3.put("daily deaths", a9);
				 JSONObject json3 = new JSONObject(hm3);
				 if(map.get("position")==2)
				 {
					 result= json3.toString();
					 return result;
				 }
		 
		 
		 
		 return result;
		
	}

}
