package com.cbtest.controller;

import com.cbtest.service.CustomerService;
import com.google.inject.Inject;

import static spark.Spark.*;

public class CustomerController {

    public CustomerController(CustomerService service){
        post("/customers", service.insertCustomer);
        get("/customers", service.getAllCustomers);
        get("/customers/:id", service.getCustomerById);
        put("/customers/:id", service.updateCustomer);
        delete("/customers/:id", service.deleteCustomer);
    }

}
