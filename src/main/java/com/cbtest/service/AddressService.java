package com.cbtest.service;

import com.cbtest.dao.AddressDao;
import com.cbtest.dao.CustomerDao;
import com.cbtest.db.Connection;
import com.cbtest.domain.Address;
import com.cbtest.domain.Customer;
import com.cbtest.dto.APIResponseDTO;
import com.cbtest.dto.AddressDTO;
import com.cbtest.enums.MessagesEnum;
import com.cbtest.enums.ResponseCodeEnum;
import com.cbtest.util.GeneralUtil;
import com.cbtest.util.JsonUtil;
import com.google.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class AddressService {


    public static Route insertAddress = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try {
            long customerId = Long.parseLong(request.params(":id"));
            AddressDTO addressDTO = JsonUtil.jsonToClass(request.body(), AddressDTO.class);
            addressDTO.setCustomerId(customerId);

            Jdbi jdbi = Connection.connect();

            Customer customer = jdbi.withExtension(CustomerDao.class, dao ->{
                return dao.findById(customerId);
            });

            if(customer == null || GeneralUtil.isEmpty(customer)){
                APIResponse.setCode(ResponseCodeEnum.CREATE_ADDRESS.getCode());
                APIResponse.setDescription(MessagesEnum.CUSTOMER_DOES_NOT_EXIST.getMessage());
                return JsonUtil.objectToJson(APIResponse);
            }

            //if the incoming address is the new main address
            //set the current main one to non-main
            if(addressDTO.getMain()){
                jdbi.withExtension(AddressDao.class, dao ->{
                    dao.setMainAddressFalseByCustomerId(customerId);
                    return 0;
                });
            }

            jdbi.withExtension(AddressDao.class, dao ->{
                return dao.insert(addressDTO);
            });
            APIResponse.setCode(ResponseCodeEnum.CREATE_ADDRESS.getCode());
            APIResponse.setDescription(MessagesEnum.INSERT_SUCCESS.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }catch (Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.CREATE_ADDRESS.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public static Route getAllCustomerAddresses = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try{

            List<Address> addresses = new ArrayList<>();
            String jsonString = new String();
            long customerId = Long.parseLong(request.params(":id"));

            Jdbi jdbi = Connection.connect();

            addresses = jdbi.withExtension(AddressDao.class, dao ->{
                return dao.findAllCustomerAddresses(customerId);
            });
            jsonString = JsonUtil.listToJson(addresses);

            return jsonString;
        }catch(Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.GET_ALL_ADDRESSES.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public static Route getCustomerAddressById = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try{
            Address address = new Address();
            String jsonString = new String();
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));

            Jdbi jdbi = Connection.connect();

            address = jdbi.withExtension(AddressDao.class, dao -> {
                return dao.findCustomerAddressById(customerId, addressId);
            });

            jsonString = JsonUtil.objectToJson(address);

            return jsonString;

        }catch (Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.GET_ADDRESS.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public static Route updateCustomerAddressById = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try {
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));
            AddressDTO addressDTO = JsonUtil.jsonToClass(request.body(), AddressDTO.class);
            addressDTO.setCustomerId(customerId);

            Jdbi jdbi = Connection.connect();

            //if the incoming address is the new main address
            //set the current main one to non-main
            if(addressDTO.getMain()){
                jdbi.withExtension(AddressDao.class, dao ->{
                    dao.setMainAddressFalseByCustomerId(customerId);
                    return 0;
                });
            }

            jdbi.withExtension(AddressDao.class, dao ->{
                dao.updateById(addressDTO, addressId);
                return 0;
            });

            APIResponse.setCode(ResponseCodeEnum.UPDATE_ADDRESS.getCode());
            APIResponse.setDescription(MessagesEnum.UPDATE_SUCCESS.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }catch (Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.UPDATE_ADDRESS.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public static Route deleteAddress = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try{
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));

            Jdbi jdbi = Connection.connect();

            jdbi.withExtension(AddressDao.class, dao ->{
                dao.deleteById(customerId, addressId);
                return 0;
            });

            APIResponse.setCode(ResponseCodeEnum.DELETE_ADDRESS.getCode());
            APIResponse.setDescription(MessagesEnum.DELETE_SUCCESS.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }catch (Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.DELETE_ADDRESS.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };
}
