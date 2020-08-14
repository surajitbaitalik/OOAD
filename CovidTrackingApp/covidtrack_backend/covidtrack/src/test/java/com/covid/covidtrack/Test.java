/**
 * 
 */
package com.covid.covidtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.covid.covidtrack.model.LocationDiseaseInfo;

/**
 * @author Surajit
 *
 */
public class Test {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Map<String,ArrayList<LocationDiseaseInfo>> hm=new HashMap<>();
		ArrayList<LocationDiseaseInfo> a1=new ArrayList<>();
		a1.add(new LocationDiseaseInfo("10-Jun","100"));
		a1.add(new LocationDiseaseInfo("11-Jun","200"));
		a1.add(new LocationDiseaseInfo("12-Jun","300"));
		a1.add(new LocationDiseaseInfo("13-Jun","400"));
		a1.add(new LocationDiseaseInfo("14-Jun","500"));
		a1.add(new LocationDiseaseInfo("15-Jun","500"));
		a1.add(new LocationDiseaseInfo("16-Jun","100"));
		a1.add(new LocationDiseaseInfo("17-Jun","100"));
		a1.add(new LocationDiseaseInfo("18-Jun","100"));
		a1.add(new LocationDiseaseInfo("19-Jun","100"));
		
		hm.put("newcases", a1);
		ArrayList<LocationDiseaseInfo> a2=new ArrayList<>();
		a2.add(new LocationDiseaseInfo("10-Jun","100"));
		a2.add(new LocationDiseaseInfo("11-Jun","200"));
		a2.add(new LocationDiseaseInfo("11-Jun","300"));
		a2.add(new LocationDiseaseInfo("12-Jun","400"));
		a2.add(new LocationDiseaseInfo("13-Jun","500"));
		a2.add(new LocationDiseaseInfo("14-Jun","500"));
		a2.add(new LocationDiseaseInfo("15-Jun","100"));
		a2.add(new LocationDiseaseInfo("16-Jun","100"));
		a2.add(new LocationDiseaseInfo("17-Jun","100"));
		a2.add(new LocationDiseaseInfo("18-Jun","100"));
		hm.put("recoveries", a2);
		ArrayList<LocationDiseaseInfo> a3=new ArrayList<>();
		a3.add(new LocationDiseaseInfo("10-Jun","10"));
		a3.add(new LocationDiseaseInfo("11-Jun","20"));
		a3.add(new LocationDiseaseInfo("12-Jun","3"));
		a3.add(new LocationDiseaseInfo("13-Jun","40"));
		a3.add(new LocationDiseaseInfo("14-Jun","5"));
		a3.add(new LocationDiseaseInfo("15-Jun","50"));
		a3.add(new LocationDiseaseInfo("16-Jun","10"));
		a3.add(new LocationDiseaseInfo("17-Jun","10"));
		a3.add(new LocationDiseaseInfo("18-Jun","10"));
		a3.add(new LocationDiseaseInfo("19-Jun","10"));
		hm.put("daily deaths", a3);
		
		
	}

}
