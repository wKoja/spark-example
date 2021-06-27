package com.cbtest;

import com.cbtest.controllers.CustomerController;
import com.cbtest.daos.AddressDao;
import com.cbtest.daos.CustomerDao;
import com.cbtest.domains.Customer;
import com.cbtest.services.CustomerService;

import java.util.List;

import static spark.Spark.*;

public class Application {



    public static void main(String[] args){

       //path() não está funcionando. Refatorar depois


        port(8080);

        get("/customers", CustomerController.getAll);

        get("/customers/:id", (request, response) -> {
            return "Customer number " + request.params(":id");
        });

    }
}
