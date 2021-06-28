package com.cbtest.service;

import com.cbtest.dao.CustomerDao;
import com.cbtest.db.Connection;
import com.cbtest.domain.Customer;
import com.cbtest.util.JsonUtil;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public static Route getAllCustomers = (request, response) -> {
        try{
        //TODO: dynamic queries for url parameters. Do it after finishing the endpoints
        QueryParamsMap queryMap = request.queryMap();
        if(request.queryMap().hasKeys()){
            String mapJson = JsonUtil.getMapJson(request.queryMap());
        }

        List<Customer> customers = new ArrayList<Customer>();
        String jsonString = new String();

        Jdbi jdbi = Connection.connect();
        customers = jdbi.withExtension(CustomerDao.class, dao -> {
            return dao.getAll();
        });
        jsonString = JsonUtil.listToJson(customers);

        return jsonString;
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };
}
