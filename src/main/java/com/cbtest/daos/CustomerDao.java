package com.cbtest.daos;

import com.cbtest.domains.Customer;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface CustomerDao {
    @SqlQuery("SELECT * FROM Customers")
    List<Customer> getAll();

    @SqlQuery("SELECT * FROM Customers WHERE id = ?")
    Customer getById(int id);

    void close();
}
