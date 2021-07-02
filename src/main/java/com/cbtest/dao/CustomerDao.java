package com.cbtest.dao;

import com.cbtest.domain.Address;
import com.cbtest.domain.Customer;
import com.cbtest.dto.CustomerDTO;
import com.cbtest.util.CustomerAddressReducer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;

public interface CustomerDao{

    @SqlQuery("SELECT CUSTOMERS.*, ADDRESSES.* FROM CUSTOMERS LEFT OUTER JOIN ADDRESSES on CUSTOMERS.id = ADDRESSES.customer_id")
    @RegisterBeanMapper(Customer.class)
    @RegisterBeanMapper(Address.class)
    @UseRowReducer(CustomerAddressReducer.class)
    List<Customer> findAllCustomers();

    @SqlQuery("SELECT CUSTOMERS.*, ADDRESSES.* FROM CUSTOMERS LEFT OUTER JOIN ADDRESSES ON CUSTOMERS.id= ADDRESSES.customer_id WHERE CUSTOMERS.id = ?")
    @RegisterBeanMapper(Customer.class)
    @RegisterBeanMapper(Address.class)
    @UseRowReducer(CustomerAddressReducer.class)
    Customer findById(long id);

    @SqlQuery("SELECT * FROM CUSTOMERS WHERE cpf = :cpf")
    @RegisterBeanMapper(Customer.class)
    Customer findByCPF(@Bind("cpf") String cpf);

    @SqlUpdate("INSERT INTO CUSTOMERS (id, uuid, name, email, birth_date, cpf, gender, created_at, updated_at) " +
            "VALUES (null, UUID(),:name, :email, :birthDate,:cpf,:gender, NOW(), null)")
    @GetGeneratedKeys("id")
    long insert(@BindFields CustomerDTO customer);

    @SqlUpdate("UPDATE CUSTOMERS SET name = :name, email = :email, birth_date = :birthDate, cpf = :cpf, gender = :gender, updated_at = NOW() WHERE id = :id")
    void updateById(@BindFields CustomerDTO customer, @Bind("id") long id);

    @SqlUpdate("DELETE FROM CUSTOMERS WHERE id = ?")
    void deleteById(long id);


}
