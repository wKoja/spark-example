package com.cbtest.controller;

import com.cbtest.service.AddressService;

import static spark.Spark.*;

public class AddressController {

    public AddressController(final AddressService service){
        post("/customers/:id/addresses", service.insertAddress);
        get("/customers/:id/addresses", service.getAllCustomerAddresses);
        get("/customers/:id/addresses/:address_id", service.getCustomerAddressById);
        put("/customers/:id/addresses/:address_id", service.updateCustomerAddressById);
        delete("/customers/:id/addresses/:address_id", service.deleteAddress);
    }

}
