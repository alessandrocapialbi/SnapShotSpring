package com.SWE_photoshoot_booking.services;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.repositories.CustomerRepository;
import com.SWE_photoshoot_booking.repositories.PhotographerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerEntity customer = customerRepository.findByEmail(username);
        if (customer != null) {
            return User.withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .roles("CUSTOMER")
                    .build();
        }

        PhotographerEntity photographer = photographerRepository.findByEmail(username);
        if (photographer != null) {
            return User.withUsername(photographer.getEmail())
                    .password(photographer.getPassword())
                    .roles("PHOTOGRAPHER")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}