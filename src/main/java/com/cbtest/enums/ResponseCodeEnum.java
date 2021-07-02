package com.cbtest.enums;

public enum ResponseCodeEnum {
    //customer
    CREATE_CUSTOMER ("create_customer"),
    GET_CUSTOMER("get_customer"),
    GET_ALL_CUSTOMERS("get_all_customers"),
    UPDATE_CUSTOMER("update_customer"),
    DELETE_CUSTOMER("delete_customer"),

    //address
    CREATE_ADDRESS ("create_address"),
    GET_ADDRESS("get_address"),
    GET_ALL_ADDRESSES("get_all_addresses"),
    UPDATE_ADDRESS("update_address"),
    DELETE_ADDRESS("delete_address")
    ;

    private final String code;

    private ResponseCodeEnum(String code){
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
