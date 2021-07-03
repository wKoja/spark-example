package com.cbtest.enums;

public enum SortEnum {
    ASC ("ASC"),
    DESC ("DESC"),
    CUSTOMER_NAME ("name"),
    CUSTOMER_BIRTH_DATE ("birth_date"),
    CUSTOMER_CREATED_AT ("created_at"),
    ADDRESS_STATE ("state"),
    ADDRESS_CITY ("city")
    ;

    private final String value;

    SortEnum(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
