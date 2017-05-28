package ib.parking.dao;

import ib.parking.model.Vehicle;

public interface VehicleDao {

    Vehicle getVehicle(String vNum);
}
