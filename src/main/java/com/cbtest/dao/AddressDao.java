package com.cbtest.dao;

import com.cbtest.domain.Address;
import com.cbtest.dto.AddressDTO;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface AddressDao {

    @SqlQuery("INSERT INTO ADDRESSES (address_id, customer_id, state, city, neighborhood, zipcode, street, number, additional_info, main) " +
            "VALUES (null, :customerId, :state, :city, :neighborhood, :zipCode, :street, :number, :additionalInformation, :main)")
    @GetGeneratedKeys("id")
    Integer insert(@BindFields AddressDTO address);

}
