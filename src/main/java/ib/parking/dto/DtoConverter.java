package ib.parking.dto;

import ib.parking.model.ParkingSlip;
import ib.parking.model.Vehicle;

public class DtoConverter {

    public static ParkingSlipDto getPSD(ParkingSlip ps) {
        ParkingSlipDto pDto = new ParkingSlipDto();
        pDto.setBillAmt(ps.getBillAmt());
        pDto.setCheckIn(ps.getCheckIn());
        pDto.setCheckOut(ps.getCheckOut());
        pDto.setId(ps.getId());
        pDto.setState(ps.getState());
        pDto.setVehicle(getVehicle(ps.getVehicle()));
        return pDto;
    }

    public static VehicalDto getVehicle(Vehicle vehical) {
        VehicalDto vDto = new VehicalDto();
        vDto.setvNumber(vehical.getvNumber());
        vDto.setvType(vehical.getvType());
        return vDto;
    }

}
