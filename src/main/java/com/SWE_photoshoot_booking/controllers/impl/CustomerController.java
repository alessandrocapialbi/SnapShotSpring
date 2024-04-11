package com.SWE_photoshoot_booking.controllers.impl;

import com.SWE_photoshoot_booking.controllers.AbstractController;
import com.SWE_photoshoot_booking.domain.dto.CustomerDto;
import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.repositories.CustomerRepository;
import com.SWE_photoshoot_booking.services.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController extends AbstractController<CustomerEntity, CustomerDto, CustomerRepository> {


    @Autowired
    public CustomerController(CustomerService customerService, Mapper<CustomerEntity, CustomerDto> customerMapper) {
        super(customerService, customerMapper);
    }


}
