package com.cbtest.util;

import com.cbtest.domain.Address;
import com.cbtest.domain.Customer;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerAddressReducer implements LinkedHashMapRowReducer<Integer, Customer> {

    //magical row reducer. Makes it possible to return nested objects
    //in this case, multiple addresses within costumer objects
    List<Address> nonMainAddresses = new ArrayList<>();
    public void accumulate(Map<Integer, Customer> map, RowView rowView){
        Customer c = map.computeIfAbsent(rowView.getColumn("id", Integer.class),
                id -> rowView.getRow(Customer.class));
        if(rowView.getColumn("address_id", Integer.class) != null){
            Address addressRow = rowView.getRow(Address.class);
            if(addressRow.getMain()){
                c.setMainAddress(rowView.getRow(Address.class));
                //clear() is removing addresses already set in the previous customer... what the fuck
                //so I'll re-instantiate it instead
                //nonMainAddresses.clear();
                nonMainAddresses = new ArrayList<>();
            }else{
                nonMainAddresses.add(rowView.getRow(Address.class));
            }
        }
        if(!nonMainAddresses.isEmpty()){
            c.setAddresses(nonMainAddresses);
        }
    }
}
