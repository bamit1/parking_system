package ib.parking.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ib.parking.dao.ChargingDao;
import ib.parking.dto.CharingSchemeDto;
import ib.parking.model.Charges;
import ib.parking.model.VehicleType;
import ib.parking.service.ChargingService;

@Service
public class ChargingServiceImpl implements ChargingService {

    @Autowired
    private ChargingDao chargingDao;

    @Override
    @Transactional
    public void setCharges(CharingSchemeDto csDto) {

        Charges charges = chargingDao.getCharges(VehicleType.valueOf(csDto.getvType()));
        if (charges != null) {
            charges.setModifiedAt(new Date());
            charges.setChargingScheme(csDto.getcType());
        } else {
            charges = new Charges();
            charges.setCreatedAt(new Date());
            charges.setModifiedAt(new Date());
            charges.setvType(VehicleType.valueOf(csDto.getvType()));
            charges.setChargingScheme(csDto.getcType());
        }
        chargingDao.save(charges);
    }

}
