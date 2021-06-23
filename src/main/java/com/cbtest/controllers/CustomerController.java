package com.cbtest.controllers;

import com.cbtest.daos.CustomerDao;
import com.cbtest.domains.Customer;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {


    public static Route getAll = (request, response) -> {
        List<Customer> customers = new ArrayList<Customer>();
        try{
            Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/test");
            jdbi.installPlugin(new SqlObjectPlugin());
            customers = jdbi.withExtension(CustomerDao.class, dao -> {
                return dao.getAll();
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return customers;
    };
}
