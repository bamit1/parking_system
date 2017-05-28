package ib.parking.dao.Impl;

import org.springframework.stereotype.Repository;

import ib.parking.dao.ParkingSlipDao;
import ib.parking.model.ParkingSlip;
import ib.parking.model.VehichleParkingState;

@Repository
public class ParkingSlipDaoImpl extends CommonDao<ParkingSlip>implements ParkingSlipDao {

    @Override
    public ParkingSlip getSlip(String vNum) {
        return (ParkingSlip) query("from ParkingSlip where vehicle.vNumber =:vNum and state=:state")
                .setParameter("vNum", vNum).setParameter("state", VehichleParkingState.IN)
                .uniqueResult();
    }

}
