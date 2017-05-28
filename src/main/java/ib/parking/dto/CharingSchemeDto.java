package ib.parking.dto;

import java.util.List;

import ib.parking.model.ChargingType;

public class CharingSchemeDto {

    private String vType;
    private List<ChargingType> cType;

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public List<ChargingType> getcType() {
        return cType;
    }

    public void setcType(List<ChargingType> cType) {
        this.cType = cType;
    }

}
