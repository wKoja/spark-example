package com.cbtest.daos;

import com.cbtest.domains.Customer;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface CustomerDao{

    @SqlQuery("SELECT * FROM CUSTOMERS")
    public List<Customer> getAll();
}
