package com.cbtest.domain;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Address {
    Integer addressId;
    String state;
    String city;
    String neighborhood;
    @ColumnName("zipcode")
    String zipCode;
    String street;
    String number;
    @ColumnName("additional_info")
    String additionalInformation;
    Boolean main;

    public Address(){

    }

    public Address(Integer addressId, String state, String city,
                   String neighborhood, String zipCode, String street,
                   String number, String additionalInformation,
                   Boolean main){
        this.addressId = addressId;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.additionalInformation = additionalInformation;
        this.main = main;
    }

    //getters & setters
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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
