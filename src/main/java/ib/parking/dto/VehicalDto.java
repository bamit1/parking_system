package ib.parking.dto;

import ib.parking.model.VehicleType;

public class VehicalDto {

    private String vNumber;
    private VehicleType vType;

    public String getvNumber() {
        return vNumber;
    }

    public void setvNumber(String vNumber) {
        this.vNumber = vNumber;
    }

    public VehicleType getvType() {
        return vType;
    }

    public void setvType(VehicleType vType) {
        this.vType = vType;
    }

}
