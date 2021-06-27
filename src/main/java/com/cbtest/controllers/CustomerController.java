package com.cbtest.controllers;

import com.cbtest.daos.CustomerDao;
import com.cbtest.domains.Customer;
import com.cbtest.utils.JsonUtil;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import spark.QueryParamsMap;
import spark.Route;

import java.util.*;

public class CustomerController {


    public static Route getAll = (request, response) -> {
        QueryParamsMap queryMap = request.queryMap();
        if(request.queryMap().hasKeys()){
            String mapJson = JsonUtil.getMapJson(request.queryMap());
        }
        List<Customer> customers = new ArrayList<Customer>();
        String jsonString = new String();

        try{
            Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/test");
            jdbi.installPlugin(new SqlObjectPlugin());
            customers = jdbi.withExtension(CustomerDao.class, dao -> {
                return dao.getAll();
            });

            jsonString = JsonUtil.listToJson(customers);

        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonString;
    };
}
