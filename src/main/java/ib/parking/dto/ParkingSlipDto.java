package ib.parking.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import ib.parking.model.VehichleParkingState;

public class ParkingSlipDto {

    private Long id;
    private VehicalDto vehicle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "IST")
    private Date checkIn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "IST")
    private Date checkOut;
    private Double billAmt;
    private VehichleParkingState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicalDto getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicalDto vehicle) {
        this.vehicle = vehicle;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Double getBillAmt() {
        return billAmt;
    }

    public void setBillAmt(Double billAmt) {
        this.billAmt = billAmt;
    }

    public VehichleParkingState getState() {
        return state;
    }

    public void setState(VehichleParkingState state) {
        this.state = state;
    }

}
