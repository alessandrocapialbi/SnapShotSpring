package com.SWE_photoshoot_booking.services.impl;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.repositories.CustomerRepository;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceAbstract implements AbstractCrudService<CustomerEntity> {
    private final CustomerRepository customerRepository;

    public CustomerServiceAbstract(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

}
