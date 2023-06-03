package com.example.fuelstationspringbootapplication.service;

import com.example.fuelstationspringbootapplication.model.Invoice;
import com.rabbitmq.client.MessageProperties;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class FuelStationService {

    private final static String QUEUE_NAME = "create-invoice";
    private final ConnectionFactory factory;

    public FuelStationService(ConnectionFactory factory) {
        this.factory = factory;
    }

    public Invoice getInvoice(int customerID) {
        return null;
    }

    public void createInvoice(int customerID){

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Create invoice for customer with ID: " + customerID;
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));

        } catch (IOException | TimeoutException e){
            e.printStackTrace();
        }




    }
}
