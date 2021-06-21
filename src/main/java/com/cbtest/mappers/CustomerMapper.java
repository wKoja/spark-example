package com.cbtest.mappers;

import com.cbtest.domains.Address;
import com.cbtest.domains.Customer;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements ResultSetMapper<Customer> {
    @Override
    public Customer map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getInt("addressId"),
                rs.getString("uuid"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getDate("birthDate"),
                rs.getString("cpf"),
                rs.getString("gender"),
                rs.getObject("mainAddress", Address.class),
                rs.getObject("address", Address.class),
                rs.getTimestamp("createdAt"),
                rs.getTimestamp("updatedAt")
                );
    }
}
