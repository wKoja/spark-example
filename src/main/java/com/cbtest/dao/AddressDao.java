package com.cbtest.dao;

import com.cbtest.domain.Address;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.List;

public interface AddressDao {
    @SqlQuery("SELECT * FROM Addresses")
    List<Address> getAll();

    @SqlQuery("SELECT * FROM Addresses WHERE id = ?")
    Address getById(int id);

    void close();
}
