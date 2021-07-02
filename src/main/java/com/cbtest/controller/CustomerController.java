package com.cbtest.controller;

import com.cbtest.service.CustomerService;

import static spark.Spark.*;

public class CustomerController {

    public CustomerController(final CustomerService service){
        post("/customers", service.insertCustomer);
        get("/customers", service.getAllCustomers);
        get("/customers/:id", service.getCustomerById);
        put("/customers/:id", service.updateCustomer);
        delete("/customers/:id", service.deleteCustomer);
    }

}
