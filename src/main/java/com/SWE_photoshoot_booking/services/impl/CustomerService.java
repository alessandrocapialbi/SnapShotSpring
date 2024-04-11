package com.SWE_photoshoot_booking.services.impl;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.repositories.CustomerRepository;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractCrudService<CustomerEntity, CustomerRepository> {

    public CustomerService(CustomerRepository customerRepository) {
        super(customerRepository);
    }

}
