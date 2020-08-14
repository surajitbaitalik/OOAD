/**
 * 
 */
package com.covid.covidtrack.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Surajit
 *
 */
@Entity
@Table(name="user_location_info")
public class UserLocationInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	 private int id;
	 @Column(name = "latitude")
	private String latide;
	 @Column(name = "longitude")
	private String longitude;
	 @Column(name = "status")
	private String status;
	 @Temporal(TemporalType.DATE)
	 @Column(name = "received_date")
	 @DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date receivedDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLatide() {
		return latide;
	}
	public void setLatide(String latide) {
		this.latide = latide;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	

}
