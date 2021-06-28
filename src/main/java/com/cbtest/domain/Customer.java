package com.cbtest.domain;

import org.jdbi.v3.core.mapper.Nested;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Customer {
    Integer id;
    String uuid;
    String name;
    String email;
    Date birthDate;
    String cpf;
    String gender;
    @Nested("addresses")
    Address mainAddress;
    //sql array
    List<Address> addresses;
    Timestamp createdAt;
    Timestamp updateAt;

    public Customer(){

    }

    public Customer(Integer id, String uuid, String name,
                    String email, Date birthDate, String cpf,
                    String gender, Address mainAddress, List<Address> addresses,
                    Timestamp createdAt, Timestamp updateAt){
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.gender = gender;
        this.mainAddress = mainAddress;
        this.addresses = addresses;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    //getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGender() {
        return gender;
    }

    public Address getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

}