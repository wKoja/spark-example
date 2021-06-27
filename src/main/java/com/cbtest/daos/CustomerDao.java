package com.cbtest.daos;

import com.cbtest.domains.Address;
import com.cbtest.domains.Customer;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;
import java.util.Map;

public interface CustomerDao{

    @SqlQuery("SELECT * FROM CUSTOMERS LEFT OUTER JOIN ADDRESSES A on A.id_address = CUSTOMERS.address_id")
    @RegisterBeanMapper(Customer.class)
    @RegisterBeanMapper(Address.class)
    @UseRowReducer(CustomerAddressReducer.class)
    List<Customer> getAll();
//    Map<Customer, Address> getAll();



    class CustomerAddressReducer implements LinkedHashMapRowReducer<Integer, Customer> {

        public void accumulate(Map<Integer, Customer> map, RowView rowView){
            Customer c = map.computeIfAbsent(rowView.getColumn("id", Integer.class),
                    id -> rowView.getRow(Customer.class));
            if(rowView.getColumn("address_id", Integer.class) != null){
                c.setMainAddress(rowView.getRow(Address.class));
            }
        }
    }
}
