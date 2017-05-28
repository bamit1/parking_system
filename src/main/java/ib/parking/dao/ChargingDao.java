package ib.parking.dao;

import ib.parking.dao.Impl.GenericDao;
import ib.parking.model.Charges;
import ib.parking.model.VehicleType;

public interface ChargingDao extends GenericDao<Charges> {

    Charges getCharges(VehicleType vType);
}
