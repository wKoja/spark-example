package com.cbtest.dao;

import com.cbtest.domain.Address;
import com.cbtest.domain.Customer;
import com.cbtest.util.CustomerAddressReducer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;

public interface CustomerDao{

    @SqlQuery("SELECT CUSTOMERS.*, ADDRESSES.* FROM CUSTOMERS, ADDRESSES INNER JOIN CUSTOMERS C on ADDRESSES.customer_id = C.id ")
    @RegisterBeanMapper(Customer.class)
    @RegisterBeanMapper(Address.class)
    @UseRowReducer(CustomerAddressReducer.class)
    List<Customer> getAll();


}
