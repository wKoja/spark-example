package com.cbtest.service;

import com.cbtest.dao.AddressDao;
import com.cbtest.dao.CustomerDao;
import com.cbtest.db.Connection;
import com.cbtest.domain.Address;
import com.cbtest.domain.Customer;
import com.cbtest.dto.AddressDTO;
import com.cbtest.dto.CustomerDTO;
import com.cbtest.util.JsonUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.jdbi.v3.core.Jdbi;
import spark.QueryParamsMap;
import spark.Route;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

//    public static Route insertCustomer = (request, response) -> {
//    }

    public static Route getCustomerById = (request, response) -> {
        try {
            long id = Long.parseLong(request.params(":id"));
            String jsonString = new String();
            Customer customer = new Customer();

            Jdbi jdbi = Connection.connect();

            customer = jdbi.withExtension(CustomerDao.class, dao ->{
                return dao.getCustomerById(id);
            });
            jsonString = JsonUtil.objectToJson(customer);
            return jsonString;

        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };

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
                return dao.getAllCustomers();
            });
            jsonString = JsonUtil.listToJson(customers);
            return jsonString;
        } catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };

    public static Route insertCustomer = (request, response) -> {
        try{
            CustomerDTO dto = JsonUtil.jsonToClass(request.body(), CustomerDTO.class);
            AddressDTO addressDTO = dto.getAddressDTO();

            //insert customer
            Jdbi jdbi = Connection.connect();
            Integer customerId = jdbi.withExtension(CustomerDao.class, dao ->{
                return dao.insert(dto);
            });

            addressDTO.setCustomerId(customerId);

            //then insert address
            jdbi.withExtension(AddressDao.class, dao ->{
                return dao.insert(addressDTO);
            });
            return "Inserido com sucesso.";
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };
}
