package com.cbtest.services;

import com.cbtest.daos.CustomerDao;
import com.cbtest.domains.Customer;
import com.cbtest.mappers.CustomerMapper;
import org.skife.jdbi.v2.DBI;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    CustomerDao dao;

    public List<Customer> getAll(){

        List<Customer> list = new ArrayList<Customer>();

        try{

            DBI dbi = new DBI("jdbc:mysql://localhost:3306/test");
            dbi.registerMapper(new CustomerMapper());

            list = dao.getAll();

            dao.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
