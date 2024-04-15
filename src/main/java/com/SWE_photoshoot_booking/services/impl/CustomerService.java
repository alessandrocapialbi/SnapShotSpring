package com.SWE_photoshoot_booking.services.impl;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.repositories.CustomerRepository;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService extends AbstractCrudService<CustomerEntity, CustomerRepository> {

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        super(customerRepository);
    }

    @Override
    public CustomerEntity partialUpdate(Long id, CustomerEntity entity) {
        entity.setCustomerID(id);
        return getRepository().findById(id).map(existingCustomer -> {
            Optional.ofNullable(entity.getName()).ifPresent(existingCustomer::setName);
            Optional.ofNullable(entity.getSurname()).ifPresent(existingCustomer::setSurname);
            Optional.ofNullable(entity.getEmail()).ifPresent(existingCustomer::setEmail);
            Optional.ofNullable(entity.getPassword()).ifPresent(existingCustomer::setPassword);
            Optional.ofNullable(entity.getTelephone()).ifPresent(existingCustomer::setTelephone);
            return getRepository().save(existingCustomer);
        }).orElseThrow(() -> new RuntimeException("Customer not exists"));
    }

    @Override
    public Page<CustomerEntity> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

}
