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
@Table(name="location_info")
public class LocationEntity {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_no", updatable = false, nullable = false)
	private long serial_No;
	@Column(name="latitude")
	private String latitude;
	@Column(name="longitude")
	private String longitude;
	@Column(name="density")
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
