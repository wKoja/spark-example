package com.cbtest.dto;

public class CustomerDTO {
    public long id;
    public String name;
    public String email;
    public String birthDate;
    public String cpf;
    public String gender;
    public AddressDTO address;

    public CustomerDTO(){

    }

    public CustomerDTO(String name, String email, String birthDate, String cpf, String gender, AddressDTO address) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.gender = gender;
        this.address = address;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AddressDTO getAddressDTO() {
        return address;
    }

    public void setAddressDTO(AddressDTO address) {
        this.address = address;
    }
}
