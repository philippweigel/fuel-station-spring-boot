package com.example.fuelstationspringbootapplication.model;

public class Invoice {

    private Integer id;
    private Integer customerId;
    private Double amountConsumedInKWh;

    public Invoice(Integer id, Integer customerId, Double amountConsumedInKWh) {
        this.id = id;
        this.customerId = customerId;
        this.amountConsumedInKWh = amountConsumedInKWh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getAmountConsumedInKWh() {
        return amountConsumedInKWh;
    }

    public void setAmountConsumedInKWh(Double amountConsumedInKWh) {
        this.amountConsumedInKWh = amountConsumedInKWh;
    }
}
