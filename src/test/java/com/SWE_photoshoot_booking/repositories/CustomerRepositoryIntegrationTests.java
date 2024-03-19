package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

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
    public void testThatCustomerCanBeCreated() {
        Customer customer = TestDataUtil.createTestCustomerA();
        underTest.save(customer);

        Optional<Customer> result = underTest.findById(customer.getCustomerID());
        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(customer);
    }

    @Test
    public void testThatMultipleCustomersCanBeCreated(){

        Customer customerA = TestDataUtil.createTestCustomerA();
        underTest.save(customerA);

        Customer customerB = TestDataUtil.createTestCustomerB();
        underTest.save(customerB);

        Customer customerC = TestDataUtil.createTestCustomerC();
        underTest.save(customerC);

        Iterable<Customer> result = underTest.findAll();
        List<Customer> expectedCustomers = Arrays.asList(customerA, customerB, customerC);

        assertIterableEquals(expectedCustomers, result);
    }
    @Test
    public void testThatCustomerCanBeUpdated(){

        Customer customerA = TestDataUtil.createTestCustomerA();
        underTest.save(customerA);
        customerA.setName("Matthew");
        underTest.save(customerA);
        Optional<Customer> result = underTest.findById(customerA.getCustomerID());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(customerA);
    }

    @Test
    public void testThatCustomerCanBeDeleted(){
        Customer customerA = TestDataUtil.createTestCustomerA();
        underTest.save(customerA);
        underTest.deleteById(customerA.getCustomerID());
        underTest.save(customerA);
        Optional<Customer> result = underTest.findById(customerA.getCustomerID());
        assertThat(result).isEmpty();
    }
}

