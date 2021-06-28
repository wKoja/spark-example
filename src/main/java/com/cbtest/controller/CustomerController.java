package com.cbtest.controller;

import com.cbtest.service.CustomerService;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class CustomerController {

    public CustomerController(final CustomerService service){
        get("/customers", service.getAllCustomers);


    }

//    public static Route getAll = (request, response) -> {
//        try{
//            return CustomerService.getAllCustomers(request, response);
//        }catch (Exception e){
//            e.printStackTrace();
//            return e.getMessage();
//        }
//    };

    public static Route insert = ((request, response) -> {

        return 0;
    });


}
