package ib.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ib.parking.dto.CharingSchemeDto;
import ib.parking.service.ChargingService;

@RestController
public class AdminController {

    @Autowired
    private ChargingService chargingService;

    @RequestMapping(value = "/parking/scheme", method = RequestMethod.POST)
    public void addChargingScheme(@RequestBody CharingSchemeDto csDto) {
        chargingService.setCharges(csDto);
    }

}