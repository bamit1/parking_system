package ib.parking.service.impl;

import static ib.parking.dto.DtoConverter.getPSD;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ib.parking.dao.ChargingDao;
import ib.parking.dao.ParkingSlipDao;
import ib.parking.dto.ParkingSlipDto;
import ib.parking.model.Charges;
import ib.parking.model.ChargingType;
import ib.parking.model.FixedType;
import ib.parking.model.ParkingSlip;
import ib.parking.model.VariableType;
import ib.parking.model.VehichleParkingState;
import ib.parking.model.Vehicle;
import ib.parking.model.VehicleType;
import ib.parking.service.ParkingService;

@Service
public class PakringServiceImpl implements ParkingService {

    @Autowired
    private ParkingSlipDao parkingSlipDao;

    @Autowired
    private ChargingDao chargingDao;

    @Override
    @Transactional
    public ParkingSlipDto checkIn(String vNum, String vType, Date checkIn) {

        ParkingSlip slip = parkingSlipDao.getSlip(vNum);
        if (slip != null) {
            throw new IllegalArgumentException("Vehicle Not CheckedOut");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setvNumber(vNum);
        vehicle.setvType(VehicleType.valueOf(vType));

        slip = new ParkingSlip();
        slip.setCheckIn(checkIn);
        slip.setState(VehichleParkingState.IN);
        slip.setVehicle(vehicle);
        parkingSlipDao.save(slip);

        return getPSD(slip);
    }

    @Override
    @Transactional
    public ParkingSlipDto checkOut(String vNum, Date checkOut) {

        ParkingSlip slip = parkingSlipDao.getSlip(vNum);
        if (slip == null) {
            throw new IllegalArgumentException("Vehicle Not CheckedIn");
        }
        slip.setCheckOut(checkOut);
        slip.setState(VehichleParkingState.OUT);

        Vehicle vehicle = slip.getVehicle();
        VehicleType vType = vehicle.getvType();


        long timeDiff = slip.getCheckOut().getTime() - slip.getCheckIn().getTime();
        if (timeDiff <= 0) {
            throw new IllegalArgumentException("Checkout time cannot be lesser than checkIn time");
        }
        Long hoursDiff = TimeUnit.MILLISECONDS.toHours(timeDiff);

        Charges charges = chargingDao.getCharges(vType);

        Long tempHrs = hoursDiff + 1;
        Double price = 0.0;
        List<ChargingType> cging = charges.getChargingScheme();
        for (ChargingType ctype : cging) {
            if (tempHrs > 0.0) {

                if (ctype instanceof FixedType) {

                    if (ctype.getHours() == null) {
                        tempHrs = -1L;
                    } else {
                        tempHrs -= ctype.getHours();
                    }
                    price += ctype.getPrice();

                } else if (ctype instanceof VariableType) {

                    if (ctype.getHours() == null) {
                        price += ctype.getPrice() * tempHrs;

                    } else {
                        Long diffHr = tempHrs - ctype.getHours();
                        price += diffHr > 0 ? ctype.getPrice() * ctype.getHours() : ctype.getPrice() * tempHrs;
                        tempHrs -= ctype.getHours();
                    }
                }
            } else {
                break;
            }
        }
        slip.setBillAmt(price);
        return getPSD(slip);
    }

}
