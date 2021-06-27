package com.cbtest.daos;

import com.cbtest.domains.Address;
import com.cbtest.domains.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface CustomerDao{

    @SqlQuery("SELECT * FROM CUSTOMERS INNER JOIN ADDRESSES A on A.address_id = CUSTOMERS.address_id")
    @RegisterBeanMapper(Customer.class)
    @RegisterBeanMapper(Address.class)
    List<Customer> getAll();



//    class CustomerAddressReducer implements LinkedHashMapRowReducer<Integer, Customer>{
//
//        public void accumulate(Map<Integer, Customer> map, RowView rowView){
//            Customer c = map.computeIfAbsent(rowView.getColumn("id", Integer.class),
//                    id -> rowView.getRow(Customer.class));
//            if(rowView.getColumn("address_id", Integer.class) != null){
//                c.setMainAddress(rowView.getRow(Address.class));
//            }
//        }
//    }
}
