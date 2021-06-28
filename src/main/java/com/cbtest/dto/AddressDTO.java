package com.cbtest.dto;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class AddressDTO {
    public Integer customerId;
    public String state;
    public String city;
    public String neighborhood;
    public String zipCode;
    public String street;
    public String number;
    public String additionalInformation;
    public Boolean main;

    public AddressDTO(){

    }

    public AddressDTO(Integer customerId, String state, String city, String neighborhood,
                      String zipCode, String street, String number, String additionalInformation, Boolean main) {
        this.customerId = customerId;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.additionalInformation = additionalInformation;
        this.main = main;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }
}
