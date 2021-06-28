package com.cbtest;

import com.cbtest.controller.CustomerController;
import com.cbtest.service.CustomerService;

import static spark.Spark.*;

public class Application {

    public static void main(String[] args){

        port(8080);
        new CustomerController(new CustomerService());

    }
}
