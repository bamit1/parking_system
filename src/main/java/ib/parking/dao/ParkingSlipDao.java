package ib.parking.dao;

import ib.parking.dao.Impl.GenericDao;
import ib.parking.model.ParkingSlip;

public interface ParkingSlipDao extends GenericDao<ParkingSlip> {

    ParkingSlip getSlip(String vNum);
}
