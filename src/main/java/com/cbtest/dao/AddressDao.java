package com.cbtest.dao;

import com.cbtest.domain.Address;
import com.cbtest.dto.AddressDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
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

    @SqlQuery("SELECT * FROM ADDRESSES WHERE customer_id = ?")
    @RegisterBeanMapper(Address.class)
    List<Address> findAllCustomerAddresses(long id);

    @SqlQuery("SELECT * FROM ADDRESSES WHERE customer_id = :customerId AND address_id = :addressId")
    @RegisterBeanMapper(Address.class)
    Address findCustomerAddressById(@Bind("customerId") long customerId, @Bind("addressId") long addressId);

    @SqlQuery("SELECT * FROM ADDRESSES WHERE customer_id = :customerId AND main = true")
    @RegisterBeanMapper(Address.class)
    Address findMainAddressByCustomerId(@Bind("customerId") long customerId);

    @SqlUpdate("UPDATE ADDRESSES SET main = false WHERE customer_id = :customerId AND main = true")
    @RegisterBeanMapper(Address.class)
    void setMainAddressFalseByCustomerId(@Bind("customerId") long customerId);

    @SqlUpdate("UPDATE ADDRESSES SET state = :state, city = :city, neighborhood = :neighborhood, zipcode = :zipCode, street = :street," +
            " number = :number, additional_info = :additionalInformation, main = :main WHERE customer_id = :customerId")
    void updateByCustomerId(@BindFields AddressDTO address, @Bind("customerId") long customerId);

    @SqlUpdate("UPDATE ADDRESSES SET state = :state, city = :city, neighborhood = :neighborhood, zipcode = :zipCode, street = :street," +
            " number = :number, additional_info = :additionalInformation, main = :main WHERE address_id = :addressId")
    void updateById(@BindFields AddressDTO address, @Bind("addressId") long addressId);

    @SqlUpdate("DELETE FROM ADDRESSES WHERE customer_id = ?")
    void deleteAllByCustomerId(long customerId);

    @SqlUpdate("DELETE FROM ADDRESSES WHERE customer_id = ? AND address_id = ?")
    void deleteById(long customerId, long addressId);

}
