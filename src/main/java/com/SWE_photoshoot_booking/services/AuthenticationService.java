package com.SWE_photoshoot_booking.services;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.repositories.CustomerRepository;
import com.SWE_photoshoot_booking.repositories.PhotographerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CustomerEntity registerCustomer(CustomerEntity customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        return customerRepository.save(customer);
    }

    public PhotographerEntity registerPhotographer(PhotographerEntity photographer) {
        String encodedPassword = passwordEncoder.encode(photographer.getPassword());
        photographer.setPassword(encodedPassword);
        return photographerRepository.save(photographer);
    }

    public CustomerEntity loginCustomer(String email, String password) {
        CustomerEntity customer = customerRepository.findByEmail(email);
        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return customer;
        }
        return null;
    }

    public PhotographerEntity loginPhotographer(String email, String password) {
        PhotographerEntity photographer = photographerRepository.findByEmail(email);
        if (photographer != null && passwordEncoder.matches(password, photographer.getPassword())) {
            return photographer;
        }
        return null;
    }
}