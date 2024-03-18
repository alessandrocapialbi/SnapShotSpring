package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerRepositoryIntegrationTests {

    private final CustomerRepository underTest;

    @Autowired
    public CustomerRepositoryIntegrationTests(CustomerRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testCreateUser() {
        Customer customer = TestDataUtil.createTestCustomer();
        underTest.save(customer);

        Optional<Customer> result = underTest.findById(customer.getCustomerID());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(customer);
    }
}

