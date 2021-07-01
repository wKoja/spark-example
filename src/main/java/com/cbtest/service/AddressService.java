package com.cbtest.service;

import com.cbtest.dao.AddressDao;
import com.cbtest.db.Connection;
import com.cbtest.domain.Address;
import com.cbtest.dto.AddressDTO;
import com.cbtest.util.JsonUtil;
import org.checkerframework.checker.units.qual.A;
import org.jdbi.v3.core.Jdbi;
import spark.Route;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class AddressService {

    public static Route insertAddress = (request, response) -> {
        try {
            long customerId = Long.parseLong(request.params(":id"));
            AddressDTO addressDTO = JsonUtil.jsonToClass(request.body(), AddressDTO.class);
            addressDTO.setCustomerId(customerId);

            Jdbi jdbi = Connection.connect();

            if(addressDTO.getMain()){
                Address address =  jdbi.withExtension(AddressDao.class, dao -> {
                    return dao.getMainAddress(customerId);
                });
                if(address != null){
                    throw new RuntimeException("Não é permitido que um cliente tenha mais de um endereço principal.");
                }
            }

            jdbi.withExtension(AddressDao.class, dao ->{
                return dao.insert(addressDTO);
            });
            return "Endereço inserido com sucesso.";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };

    public static Route getAllCustomerAddresses = (request, response) -> {
        try{

            List<Address> addresses = new ArrayList<>();
            String jsonString = new String();
            long customerId = Long.parseLong(request.params(":id"));

            Jdbi jdbi = Connection.connect();

            addresses = jdbi.withExtension(AddressDao.class, dao ->{
                return dao.getAllCustomerAddresses(customerId);
            });
            jsonString = JsonUtil.listToJson(addresses);

            return jsonString;
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };

    public static Route getCustomerAddressById = (request, response) -> {
        try{
            Address address = new Address();
            String jsonString = new String();
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));

            Jdbi jdbi = Connection.connect();

            address = jdbi.withExtension(AddressDao.class, dao -> {
                return dao.getCustomerAddressById(customerId, addressId);
            });

            jsonString = JsonUtil.objectToJson(address);

            return jsonString;

        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };

    public static Route updateCustomerAddressById = (request, response) -> {
        try {
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));
            AddressDTO addressDTO = JsonUtil.jsonToClass(request.body(), AddressDTO.class);
            addressDTO.setCustomerId(customerId);

            Jdbi jdbi = Connection.connect();

            //if the incoming address is the new main address
            //set the current one to additional address
            if(addressDTO.getMain()){
                jdbi.withExtension(AddressDao.class, dao ->{
                    dao.setMainAddressFalse(customerId);
                    return 0;
                });
            }

            jdbi.withExtension(AddressDao.class, dao ->{
                dao.updateById(addressDTO, addressId);
                return 0;
            });

            return "Endereço atualizado com sucesso.";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };

    public static Route deleteAddress = (request, response) -> {
        try{
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));

            Jdbi jdbi = Connection.connect();

            jdbi.withExtension(AddressDao.class, dao ->{
                dao.delete(customerId, addressId);
                return 0;
            });

            return "Endereço deletado com sucesso";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    };
}
