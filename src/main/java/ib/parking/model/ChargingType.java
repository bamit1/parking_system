package ib.parking.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import ib.parking.dto.PriceDto;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = FixedType.class, name = "ft"),
    @JsonSubTypes.Type(value = VariableType.class, name = "vt")
})
public abstract class ChargingType {

    private String cType;
    private Long hours;
    private Double price;

    public abstract void calculatePrice(PriceDto pDto);

    protected ChargingType(String cType) {
        this.setcType(cType);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }
}
