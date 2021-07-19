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
import org.jdbi.v3.core.Jdbi;
import spark.Route;

import java.util.List;

public class AddressService {

    private APIResponseDTO APIResponse;
    private Jdbi jdbi = Connection.connect();

    public Route insertAddress = (request, response) -> {
        try {
            long customerId = Long.parseLong(request.params(":id"));
            AddressDTO addressDTO = JsonUtil.jsonToClass(request.body(), AddressDTO.class);
            addressDTO.setCustomerId(customerId);

            Customer customer = jdbi.withExtension(CustomerDao.class, dao -> dao.findById(customerId));

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

            jdbi.withExtension(AddressDao.class, dao -> dao.insert(addressDTO));
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

    public Route getAllCustomerAddresses = (request, response) -> {
        try{

            List<Address> addresses;
            String jsonString;
            long customerId = Long.parseLong(request.params(":id"));

            addresses = jdbi.withExtension(AddressDao.class, dao -> dao.findAllCustomerAddresses(customerId));
            jsonString = JsonUtil.listToJson(addresses);

            return jsonString;
        }catch(Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.GET_ALL_ADDRESSES.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public Route getCustomerAddressById = (request, response) -> {
        try{
            Address address;
            String jsonString;
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));

            address = jdbi.withExtension(AddressDao.class, dao -> dao.findCustomerAddressById(customerId, addressId));

            jsonString = JsonUtil.objectToJson(address);

            return jsonString;

        }catch (Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.GET_ADDRESS.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public Route updateCustomerAddressById = (request, response) -> {
        try {
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));
            AddressDTO addressDTO = JsonUtil.jsonToClass(request.body(), AddressDTO.class);
            addressDTO.setCustomerId(customerId);

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

    public Route deleteAddress = (request, response) -> {
        try{
            long customerId = Long.parseLong(request.params(":id"));
            long addressId = Long.parseLong(request.params(":address_id"));

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
