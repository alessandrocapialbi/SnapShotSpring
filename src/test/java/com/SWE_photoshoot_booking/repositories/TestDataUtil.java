package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.Customer;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static Customer createTestCustomer() {
        return Customer.builder()
                .customerID(1L)
                .name("Jack")
                .surname("Sparrow")
                .email("jack.sparrow@example.com")
                .password("12345678")
                .telephone("3980878782").build();
        }
    }
