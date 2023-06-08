package com.example.fuelstationspringbootapplication.service;

import com.example.fuelstationspringbootapplication.exception.CustomerNotFoundException;
import com.example.fuelstationspringbootapplication.model.Invoice;
import com.example.fuelstationspringbootapplication.util.FileHandler;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

@Service
public class FuelStationService {

    private final static String QUEUE_NAME = "create-invoice";
    private final ConnectionFactory factory;
    private final FileHandler fileHandler;

    private final static String DOWNLOADS_FOLDER = System.getProperty("user.home") + "\\Downloads";

    public FuelStationService(ConnectionFactory factory, FileHandler fileHandler) {
        this.factory = factory;
        this.fileHandler = fileHandler;
    }

    public Invoice getInvoice(int customerID) {

        File downloadFolder = new File(DOWNLOADS_FOLDER);

        File invoiceFile = Arrays.stream(downloadFolder.listFiles())
                .filter(file -> file.getName().startsWith("Invoice_" + customerID))
                .findFirst()
                .orElse(null);

        if (invoiceFile != null) {
            String filePath = invoiceFile.getAbsolutePath();
            String downloadLink = fileHandler.createDownloadLink(filePath);
            String creationTime = fileHandler.getCreationTime(invoiceFile);
            return new Invoice(downloadLink, creationTime);
        } else {
            throw new CustomerNotFoundException(customerID);
        }
    }

    public void createInvoice(int customerID){

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = Integer.toString(customerID);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

        } catch (IOException | TimeoutException e){
            e.printStackTrace();
        }




    }
}
