package com.example.fuelstationspringbootapplication.controller;

import com.example.fuelstationspringbootapplication.model.Invoice;
import com.example.fuelstationspringbootapplication.service.FuelStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.example.fuelstationspringbootapplication.exception.CustomerNotFoundException;

@WebMvcTest(FuelStationController.class)
public class FuelStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuelStationService fuelStationService;

    @Test
    public void createInvoiceShouldInvokeFuelStationService() throws Exception {
        int customerID = 1;

        mockMvc.perform(post("/invoices/" + customerID))
                .andExpect(status().isOk());

        verify(fuelStationService, times(1)).createInvoice(customerID);
    }

    @Test
    public void getInvoiceShouldReturnNotFoundWhenCustomerIsNotFound() throws Exception {
        int customerID = 5;

        when(fuelStationService.getInvoice(customerID)).thenThrow(new CustomerNotFoundException(customerID));

        mockMvc.perform(get("/invoices/" + customerID))
                .andExpect(status().isNotFound());
    }
}
