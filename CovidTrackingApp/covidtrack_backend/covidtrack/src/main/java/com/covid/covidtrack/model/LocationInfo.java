/**
 * 
 */
package com.covid.covidtrack.model;



/**
 * @author Surajit
 *
 */
//@JsonFormat
public class LocationInfo {
private long serial_No;
	
	private String latitude;
	
	private String longitude;

	private int density;
	public long getSerial_No() {
		return serial_No;
	}
	public void setSerial_No(long serial_No) {
		this.serial_No = serial_No;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getDensity() {
		return density;
	}
	public void setDensity(int density) {
		this.density = density;
	}

}
