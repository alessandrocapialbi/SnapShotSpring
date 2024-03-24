package com.SWE_photoshoot_booking.controllers;
import com.SWE_photoshoot_booking.domain.dto.CustomerDto;
import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    private final Mapper<CustomerEntity, CustomerDto> customerMapper;

    public CustomerController(CustomerService customerService, Mapper<CustomerEntity, CustomerDto> customerMapper){
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping( path = "/customers")
    public CustomerDto createCustomer(@RequestBody CustomerDto customer){
            CustomerEntity customerEntity = customerMapper.mapFrom(customer);
            CustomerEntity savedCustomerEntity = customerService.createCustomer(customerEntity);
            return customerMapper.mapTo(savedCustomerEntity);
    }

}
