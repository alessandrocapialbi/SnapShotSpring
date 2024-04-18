package com.SWE_photoshoot_booking.services.impl;

import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.repositories.UserRepository;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends AbstractCrudService<UserEntity, UserRepository> {

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public UserEntity partialUpdate(UUID id, UserEntity entity) {
        entity.setUserID(id);
        return getRepository().findById(id).map(existingCustomer -> {
            Optional.ofNullable(entity.getName()).ifPresent(existingCustomer::setName);
            Optional.ofNullable(entity.getSurname()).ifPresent(existingCustomer::setSurname);
            Optional.ofNullable(entity.getEmail()).ifPresent(existingCustomer::setEmail);
            Optional.ofNullable(entity.getPassword()).ifPresent(existingCustomer::setPassword);
            Optional.ofNullable(entity.getTelephone()).ifPresent(existingCustomer::setTelephone);
            return getRepository().save(existingCustomer);
        }).orElseThrow(() -> new RuntimeException("User not exists"));
    }

    public UserEntity findCustomerByEmail(String email) {
        return getRepository().findByEmail(email);
    }


}
