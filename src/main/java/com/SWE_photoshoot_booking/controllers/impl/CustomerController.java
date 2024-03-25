package com.SWE_photoshoot_booking.controllers.impl;
import com.SWE_photoshoot_booking.domain.dto.CustomerDto;
import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.impl.CustomerServiceAbstract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerServiceAbstract customerService;

    private final Mapper<CustomerEntity, CustomerDto> customerMapper;

    public CustomerController(CustomerServiceAbstract customerService, Mapper<CustomerEntity, CustomerDto> customerMapper){
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping( path = "/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer){
            CustomerEntity customerEntity = customerMapper.mapFrom(customer);
            CustomerEntity savedCustomerEntity = customerService.create(customerEntity);
            return new ResponseEntity<>(customerMapper.mapTo(savedCustomerEntity), HttpStatus.CREATED);
    }

}
