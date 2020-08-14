/**
 * 
 */
package com.covid.covidtrack.model;

/**
 * @author Surajit
 *
 */
public class LocationDiseaseInfo {
	public String date;
	public String number;
	public LocationDiseaseInfo(String date, String number) {
		super();
		this.date = date;
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	

}
