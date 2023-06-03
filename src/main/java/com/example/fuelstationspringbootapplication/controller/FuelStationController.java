package com.example.fuelstationspringbootapplication.controller;

import com.example.fuelstationspringbootapplication.exception.CustomerNotFoundException;
import com.example.fuelstationspringbootapplication.model.Invoice;
import com.example.fuelstationspringbootapplication.service.FuelStationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FuelStationController {

    private final FuelStationService fuelStationService;

    public FuelStationController(FuelStationService fuelStationService) {
        this.fuelStationService = fuelStationService;
    }

    @PostMapping("/invoices/{customerID}")
    public void createInvoice(@PathVariable int customerID){
        fuelStationService.createInvoice(customerID);
    }

    @GetMapping("/invoices/{customerID}")
    public Invoice getInvoice(@PathVariable int customerID){
        try{
            return fuelStationService.getInvoice(customerID);
        }
        catch (CustomerNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }


    }

}
