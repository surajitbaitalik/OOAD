/**
 * 
 */
package com.covid.covidtrack.model;



/**
 * @author Surajit
 *
 */

public class ExternalUser {
	
	private long token_id;
	
	private String userLatitude;
	
	private String userLongitude;
	
	private String userdiseaseStatus;
	private int distance;
	
	private int age;
	
	private boolean isAlarm;
	
	private boolean isWarning;

	public long getToken_id() {
		return token_id;
	}

	public void setToken_id(long token_id) {
		this.token_id = token_id;
	}

	public String getUserLatitude() {
		return userLatitude;
	}

	public void setUserLatitude(String userLatitude) {
		this.userLatitude = userLatitude;
	}

	public String getUserLongitude() {
		return userLongitude;
	}

	public void setUserLongitude(String userLongitude) {
		this.userLongitude = userLongitude;
	}

	public String getUserdiseaseStatus() {
		return userdiseaseStatus;
	}

	public void setUserdiseaseStatus(String userdiseaseStatus) {
		this.userdiseaseStatus = userdiseaseStatus;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isAlarm() {
		return isAlarm;
	}

	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}

	public boolean isWarning() {
		return isWarning;
	}

	public void setWarning(boolean isWarning) {
		this.isWarning = isWarning;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	

}
