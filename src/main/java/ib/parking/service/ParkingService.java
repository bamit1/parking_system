package ib.parking.service;

import java.util.Date;

import ib.parking.dto.ParkingSlipDto;

public interface ParkingService {

    ParkingSlipDto checkIn(String vNum, String vType, Date checkIn);

    ParkingSlipDto checkOut(String vNum, Date checkOut);
}
