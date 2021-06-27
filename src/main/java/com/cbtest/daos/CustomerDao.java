package com.cbtest.daos;

import com.cbtest.domains.Address;
import com.cbtest.domains.Customer;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CustomerDao{

    @SqlQuery("SELECT CUSTOMERS.*, ADDRESSES.* FROM CUSTOMERS, ADDRESSES INNER JOIN CUSTOMERS C on ADDRESSES.customer_id = C.id ")
    @RegisterBeanMapper(Customer.class)
    @RegisterBeanMapper(Address.class)
    @UseRowReducer(CustomerAddressReducer.class)
    List<Customer> getAll();
//    Map<Customer, Address> getAll();



    class CustomerAddressReducer implements LinkedHashMapRowReducer<Integer, Customer> {
        List<Address> nonMainAddresses = new ArrayList<>();
        public void accumulate(Map<Integer, Customer> map, RowView rowView){
            Customer c = map.computeIfAbsent(rowView.getColumn("id", Integer.class),
                    id -> rowView.getRow(Customer.class));
            if(rowView.getColumn("address_id", Integer.class) != null){
                Address addressRow = rowView.getRow(Address.class);
                if(addressRow.getMain()){
                    c.setMainAddress(rowView.getRow(Address.class));
                }else{
                    nonMainAddresses.add(rowView.getRow(Address.class));
                }
            }
            if(!nonMainAddresses.isEmpty()){
                c.setAddresses(nonMainAddresses);
            }
        }
    }
}
