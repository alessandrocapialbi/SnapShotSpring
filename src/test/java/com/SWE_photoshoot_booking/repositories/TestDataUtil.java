package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static CustomerEntity createTestCustomerA() {
        return CustomerEntity.builder()
                .customerID(1L)
                .name("Jack")
                .surname("Sparrow")
                .email("jack.sparrow@example.com")
                .password("12345678")
                .telephone("+393980878782").build();
        }

    public static CustomerEntity createTestCustomerB() {
        return CustomerEntity.builder()
                .customerID(2L)
                .name("Alessandro")
                .surname("Capialbi")
                .email("capialbi.alessandro@example.com")
                .password("75899898")
                .telephone("+3937745898744").build();
    }

    public static CustomerEntity createTestCustomerC() {
        return CustomerEntity.builder()
                .customerID(3L)
                .name("Bob")
                .surname("King")
                .email("bob.king@example.com")
                .password("34567875")
                .telephone("+13980878782").build();
    }
    }
