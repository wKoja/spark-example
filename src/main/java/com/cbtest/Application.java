package com.cbtest;

import com.cbtest.daos.AddressDao;
import com.cbtest.daos.CustomerDao;
import com.cbtest.services.CustomerService;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args){

        CustomerService customerService;
       //path() não está funcionando. Refatorar depois

        port(8080);

        get("/customers", (request, response) -> {
            return customerService.getAll();
        });

        get("/customers/:id", (request, response) -> {
            return "Customer number " + request.params(":id");
        });

    }
}
