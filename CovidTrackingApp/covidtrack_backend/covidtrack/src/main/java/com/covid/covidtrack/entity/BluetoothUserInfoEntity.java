/**
 * 
 */
package com.covid.covidtrack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Surajit
 *
 */
@Entity
@Table(name="user_bluetooth_info")
public class BluetoothUserInfoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenid", updatable = false, nullable = false)
	private long token_id;
	@Column(name="user_latitude")
	private String userLatitude;
	@Column(name="user_longitude")
	private String userLongitude;
	@Column(name="disease_status")
	private String userdiseaseStatus;
	@Column(name="age")
	private int age;
	@Column(name="distance")
	private int distance;
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public long getToken_id() {
		return token_id;
	}
	public void setToken_id(long token_id) {
		this.token_id = token_id;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	

}
