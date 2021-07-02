package com.cbtest.service;

import com.cbtest.dao.AddressDao;
import com.cbtest.dao.CustomerDao;
import com.cbtest.db.Connection;
import com.cbtest.domain.Customer;
import com.cbtest.dto.APIResponseDTO;
import com.cbtest.dto.AddressDTO;
import com.cbtest.dto.CustomerDTO;
import com.cbtest.enums.LimitsEnum;
import com.cbtest.enums.MessagesEnum;
import com.cbtest.enums.ResponseCodeEnum;
import com.cbtest.util.GeneralUtil;
import com.cbtest.util.JsonUtil;
import org.jdbi.v3.core.Jdbi;
import spark.QueryParamsMap;
import spark.Route;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public Route insertCustomer = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try{
            CustomerDTO dto = JsonUtil.jsonToClass(request.body(), CustomerDTO.class);
            AddressDTO addressDTO = dto.getAddressDTO();

            APIResponse = runInsertValidations(dto);
            if(!GeneralUtil.isEmpty(APIResponse)){
                return JsonUtil.objectToJson(APIResponse);
            }

            //insert customer
            Jdbi jdbi = Connection.connect();
            long customerId = jdbi.withExtension(CustomerDao.class, dao ->{
                return dao.insert(dto);
            });

            addressDTO.setCustomerId(customerId);
            addressDTO.setMain(true);

            //then insert address
            jdbi.withExtension(AddressDao.class, dao ->{
                return dao.insert(addressDTO);
            });

            APIResponse.setCode(ResponseCodeEnum.CREATE_CUSTOMER.getCode());
            APIResponse.setDescription(MessagesEnum.INSERT_SUCCESS.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }catch(Exception e){
            e.printStackTrace();

            APIResponse.setCode(ResponseCodeEnum.CREATE_CUSTOMER.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public static Route getAllCustomers = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try{
            //TODO: dynamic queries for url parameters. Do it after finishing the endpoints
            QueryParamsMap queryMap = request.queryMap();
            if(request.queryMap().hasKeys()){
                String mapJson = JsonUtil.getMapJson(request.queryMap());
            }

            List<Customer> customers = new ArrayList<Customer>();
            String jsonString = new String();

            Jdbi jdbi = Connection.connect();
            customers = jdbi.withExtension(CustomerDao.class, dao -> {
                return dao.findAllCustomers();
            });
            jsonString = JsonUtil.listToJson(customers);
            return jsonString;
        } catch (Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.GET_ALL_CUSTOMERS.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public static Route getCustomerById = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try {
            long id = Long.parseLong(request.params(":id"));
            String jsonString = new String();
            Customer customer = new Customer();

            Jdbi jdbi = Connection.connect();

            customer = jdbi.withExtension(CustomerDao.class, dao ->{
                return dao.findById(id);
            });
            jsonString = JsonUtil.objectToJson(customer);
            return jsonString;

        }catch (Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.GET_CUSTOMER.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public Route updateCustomer = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try{
            long customerId = Integer.parseInt(request.params(":id"));
            CustomerDTO customerDTO = JsonUtil.jsonToClass(request.body(), CustomerDTO.class);
            AddressDTO addressDTO = customerDTO.getAddressDTO();
            addressDTO.setCustomerId(customerId);

            Jdbi jdbi = Connection.connect();

            //update customer
            if(customerDTO != null && !GeneralUtil.isEmpty(customerDTO)){
                jdbi.withExtension(CustomerDao.class, dao ->{
                    dao.updateById(customerDTO, customerId);
                    return 0;
                });
            }else {
                APIResponse.setCode(ResponseCodeEnum.UPDATE_CUSTOMER.getCode());
                APIResponse.setDescription(MessagesEnum.NO_CUSTOMER.getMessage());
                return JsonUtil.objectToJson(APIResponse);
            }

            //then update address
            if(addressDTO != null && !GeneralUtil.isEmpty(addressDTO)){
                jdbi.withExtension(AddressDao.class, dao -> {
                    dao.updateByCustomerId(addressDTO, customerId);
                    return 0;
                });
            }

            APIResponse.setCode(ResponseCodeEnum.UPDATE_CUSTOMER.getCode());
            APIResponse.setDescription(MessagesEnum.UPDATE_SUCCESS.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }catch(Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.UPDATE_CUSTOMER.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    public static Route deleteCustomer = (request, response) -> {
        APIResponseDTO APIResponse = new APIResponseDTO();
        try{
            long customerId = Integer.parseInt(request.params(":id"));

            Jdbi jdbi = Connection.connect();

            jdbi.withExtension(AddressDao.class, dao ->{
                dao.deleteAllByCustomerId(customerId);
                return 0;
            });

            jdbi.withExtension(CustomerDao.class, dao ->{
                dao.deleteById(customerId);
                return 0;
            });

            APIResponse.setCode(ResponseCodeEnum.DELETE_CUSTOMER.getCode());
            APIResponse.setDescription(MessagesEnum.DELETE_SUCCESS.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }catch(Exception e){
            e.printStackTrace();
            APIResponse.setCode(ResponseCodeEnum.DELETE_CUSTOMER.getCode());
            APIResponse.setDescription(e.getMessage());
            return JsonUtil.objectToJson(APIResponse);
        }
    };

    //validations

    private APIResponseDTO runInsertValidations(CustomerDTO dto){
        APIResponseDTO APIResponse = new APIResponseDTO();

        if(dto == null || GeneralUtil.isEmpty(dto)){
            APIResponse.setCode(ResponseCodeEnum.CREATE_CUSTOMER.getCode());
            APIResponse.setDescription(MessagesEnum.NO_CUSTOMER.getMessage());
            return APIResponse;
        }

        if(dto.getAddressDTO() == null || GeneralUtil.isEmpty(dto.getAddressDTO())){
            APIResponse.setCode(ResponseCodeEnum.CREATE_CUSTOMER.getCode());
            APIResponse.setDescription(MessagesEnum.NO_MAIN_ADDRESS.getMessage());
            return APIResponse;
        }

        if(!this.validateAge(dto.getBirthDate())){
            APIResponse.setCode(ResponseCodeEnum.CREATE_CUSTOMER.getCode());
            APIResponse.setDescription(MessagesEnum.INVALID_AGE.getMessage());
            return APIResponse;
        };

        if(!this.validateCPF(dto.getCpf())){
            APIResponse.setCode(ResponseCodeEnum.CREATE_CUSTOMER.getCode());
            APIResponse.setDescription(MessagesEnum.INVALID_CPF.getMessage());
            return APIResponse;
        }

        if(!this.validateExistingCustomer(dto.getCpf())){
            APIResponse.setCode(ResponseCodeEnum.CREATE_CUSTOMER.getCode());
            APIResponse.setDescription(MessagesEnum.CUSTOMER_EXISTS.getMessage());
            return APIResponse;
        }
        return APIResponse;
    };

    private boolean validateAge(String birthDateString){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime birthDate = LocalDateTime.parse(birthDateString, formatter);
            LocalDateTime now = LocalDateTime.now();
            long diffYears = (long) Math.floor(Duration.between(birthDate, now).toDays()/365);
            if(diffYears > LimitsEnum.MAX_AGE.value()){
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        };
        return true;
    };

    //sort of
    private boolean validateCPF(String cpf){
        long len = cpf.length();
        final long maxCPFMasked = LimitsEnum.MAX_CPF_SIZE_MASKED.value();
        final long maxCPF = LimitsEnum.MAX_CPF_SIZE_NOT_MASKED.value();

        try{
            if(len > maxCPFMasked
               || (len > maxCPF && len < maxCPFMasked)
               || len < maxCPF){
                return false;
            }

           return  true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    };

    private boolean validateExistingCustomer(String cpf){
        long len = cpf.length();
        final long maxCPFMasked = LimitsEnum.MAX_CPF_SIZE_MASKED.value();
        final long maxCPF = LimitsEnum.MAX_CPF_SIZE_NOT_MASKED.value();
        try{

            Jdbi jdbi = Connection.connect();

            Customer existingCustomer = jdbi.withExtension(CustomerDao.class, dao ->{
                if(len == maxCPFMasked){
                    String filterdCPF = this.filterCPF(cpf);
                    return dao.findByCPF(cpf);
                }
                return dao.findByCPF(cpf);
            });
            if(existingCustomer != null || !GeneralUtil.isEmpty(existingCustomer)) {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    };

    private String filterCPF(String maskedCPF){
        return maskedCPF.replaceAll("-", "").replaceAll(".","");
    };
}

