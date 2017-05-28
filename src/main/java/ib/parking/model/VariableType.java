package ib.parking.model;

import ib.parking.dto.PriceDto;

public class VariableType extends ChargingType {

    VariableType() {
        super("vType");
    }

    @Override
    public void calculatePrice(PriceDto pDto) {
        if (getHours() == null) {
            pDto.price += getPrice() * pDto.hrs;

        } else {
            Long diffHr = pDto.hrs - getHours();
            pDto.price += diffHr > 0 ? getPrice() * getHours() : getPrice() * pDto.hrs;
            pDto.hrs -= getHours();
        }
    }

}
