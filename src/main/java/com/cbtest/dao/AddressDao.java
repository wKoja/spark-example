package com.cbtest.dao;

import com.cbtest.domain.Address;
import com.cbtest.dto.AddressDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface AddressDao {

    @SqlUpdate("INSERT INTO ADDRESSES (address_id, customer_id, state, city, neighborhood, zipcode, street, number, additional_info, main) " +
            "VALUES (null, :customerId, :state, :city, :neighborhood, :zipCode, :street, :number, :additionalInformation, :main)")
    @GetGeneratedKeys("id")
    long insert(@BindFields AddressDTO address);

    @SqlUpdate("UPDATE ADDRESSES SET state = :state, city = :city, neighborhood = :neighborhood, zipcode = :zipCode, street = :street," +
            " number = :number, additional_info = :additionalInformation, main = :main WHERE customer_id = :customerId")
    @GetGeneratedKeys("id")
    long update(@BindFields AddressDTO address);

    @SqlUpdate("DELETE FROM ADDRESSES WHERE customer_id = ?")
    void delete(long id);

}
