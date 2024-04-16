package com.SWE_photoshoot_booking.services;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.repositories.CustomerRepository;
import com.SWE_photoshoot_booking.repositories.PhotographerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final CustomerRepository customerRepository;

    private final PhotographerRepository photographerRepository;

    @Autowired
    public AuthenticationService(CustomerRepository customerRepository, PhotographerRepository photographerRepository) {
        this.customerRepository = customerRepository;
        this.photographerRepository = photographerRepository;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerCustomer(CustomerEntity customer) {
        logger.info("registerCustomer called with email: {}", customer.getEmail());
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
        logger.info("Customer registration successful for email: {}", customer.getEmail());
    }

    public void registerPhotographer(PhotographerEntity photographer) {
        String encodedPassword = passwordEncoder.encode(photographer.getPassword());
        photographer.setPassword(encodedPassword);
        photographerRepository.save(photographer);
        logger.info("Photographer registration successful for email: {}", photographer.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerEntity customer = customerRepository.findByEmail(username);
        if (customer != null) {
            return User.withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .roles("customer")
                    .build();
        }

        PhotographerEntity photographer = photographerRepository.findByEmail(username);
        if (photographer != null) {
            return User.withUsername(photographer.getEmail())
                    .password(photographer.getPassword())
                    .roles("photographer")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}