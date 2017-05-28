package ib.parking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ib.parking.dto.ParkingSlipDto;
import ib.parking.service.ParkingService;

@RestController
public class UserController {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ParkingService parkingService;

    @RequestMapping(value = "/parking/checkin", method = RequestMethod.POST)
    public ParkingSlipDto checkInVehicle(@RequestParam("vNum") String vNum, @RequestParam("vType") String vType,
            @RequestParam(value = "checkIn") String checkIn) {

        Date checkInDate;
        try {
            checkInDate = DATE_FORMAT.parse(checkIn);
            return parkingService.checkIn(vNum, vType, checkInDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
            throw new IllegalArgumentException("Invalid Date Format");
        }
    }

    @RequestMapping(value = "/parking/checkout", method = RequestMethod.POST)
    public ParkingSlipDto checkOutVehicle(@RequestParam("vNum") String vNum,
            @RequestParam(value = "checkOut") String checkOut) {

        Date checkOutDate;
        try {
            checkOutDate = DATE_FORMAT.parse(checkOut);
            return parkingService.checkOut(vNum, checkOutDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
            throw new IllegalArgumentException("Invalid Date Format");
        }
    }

}
