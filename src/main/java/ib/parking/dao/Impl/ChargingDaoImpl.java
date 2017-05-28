package ib.parking.dao.Impl;

import org.springframework.stereotype.Repository;

import ib.parking.dao.ChargingDao;
import ib.parking.model.Charges;
import ib.parking.model.VehicleType;

@Repository
public class ChargingDaoImpl extends CommonDao<Charges>implements ChargingDao {

    @Override
    public Charges getCharges(VehicleType vType) {
        return (Charges) query("from Charges where active =:active and vType=:vType")
                .setParameter("active", Boolean.TRUE).setParameter("vType", vType)
                .uniqueResult();
    }

}
