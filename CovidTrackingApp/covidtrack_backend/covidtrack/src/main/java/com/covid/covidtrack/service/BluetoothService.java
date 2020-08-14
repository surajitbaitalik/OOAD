/**
 * 
 */
package com.covid.covidtrack.service;

import com.covid.covidtrack.model.ExternalUser;

/**
 * @author Surajit
 *
 */
public interface BluetoothService {
	public ExternalUser getBluetoothToken(long id);
	public ExternalUser analyzeToken(ExternalUser bUser);
	

}
