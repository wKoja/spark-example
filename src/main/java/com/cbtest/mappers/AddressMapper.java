package com.cbtest.mappers;

import com.cbtest.domains.Address;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements ResultSetMapper<Address> {
    @Override
    public Address map(int i, ResultSet rs, StatementContext sc) throws SQLException{
        return new Address(
                rs.getInt("id"),
                rs.getString("state"),
                rs.getString("city"),
                rs.getString("neighborhood"),
                rs.getString("zipCode"),
                rs.getString("street"),
                rs.getString("number"),
                rs.getString("additionalInformation"),
                rs.getBoolean("main")
        );
    }
}
