package com.covid.covidtrack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.covid.covidtrack.entity.BluetoothUserInfoEntity;

@Repository("bluetoothRepository")
public interface BluetoothRepository extends CrudRepository<BluetoothUserInfoEntity, Long> {

}
