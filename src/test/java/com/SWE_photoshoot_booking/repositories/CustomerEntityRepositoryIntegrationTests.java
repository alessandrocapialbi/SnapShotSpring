package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
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
public class CustomerEntityRepositoryIntegrationTests {

    private final CustomerRepository underTest;

    @Autowired
    public CustomerEntityRepositoryIntegrationTests(CustomerRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatCustomerCanBeCreated() {
        CustomerEntity customerEntity = TestDataUtil.createTestCustomerA();
        underTest.save(customerEntity);

        Optional<CustomerEntity> result = underTest.findById(customerEntity.getCustomerID());
        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(customerEntity);
    }

    @Test
    public void testThatMultipleCustomersCanBeCreated(){

        CustomerEntity customerEntityA = TestDataUtil.createTestCustomerA();
        underTest.save(customerEntityA);

        CustomerEntity customerEntityB = TestDataUtil.createTestCustomerB();
        underTest.save(customerEntityB);

        CustomerEntity customerEntityC = TestDataUtil.createTestCustomerC();
        underTest.save(customerEntityC);

        Iterable<CustomerEntity> result = underTest.findAll();
        List<CustomerEntity> expectedCustomerEntities = Arrays.asList(customerEntityA, customerEntityB, customerEntityC);

        assertIterableEquals(expectedCustomerEntities, result);
    }
    @Test
    public void testThatCustomerCanBeUpdated(){

        CustomerEntity customerEntityA = TestDataUtil.createTestCustomerA();
        underTest.save(customerEntityA);
        customerEntityA.setName("Matthew");
        underTest.save(customerEntityA);
        Optional<CustomerEntity> result = underTest.findById(customerEntityA.getCustomerID());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(customerEntityA);
    }

    @Test
    public void testThatCustomerCanBeDeleted(){
        CustomerEntity customerEntityA = TestDataUtil.createTestCustomerA();
        underTest.save(customerEntityA);
        underTest.deleteById(customerEntityA.getCustomerID());
        underTest.save(customerEntityA);
        Optional<CustomerEntity> result = underTest.findById(customerEntityA.getCustomerID());
        assertThat(result).isEmpty();
    }
}

