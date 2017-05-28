package ib.parking.model;

import ib.parking.dto.PriceDto;

public class FixedType extends ChargingType {

    FixedType() {
        super("fType");
    }

    @Override
    public void calculatePrice(PriceDto pDto) {

        if (getHours() == null) {
            pDto.hrs = -1L;
        } else {
            pDto.hrs -= getHours();
        }
        pDto.price += getPrice();
    }

}
