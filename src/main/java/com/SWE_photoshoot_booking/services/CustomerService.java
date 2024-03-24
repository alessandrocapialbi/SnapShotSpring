package com.SWE_photoshoot_booking.services;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerEntity createCustomer(CustomerEntity customerEntity);
}
