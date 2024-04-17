package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.entities.UserEntity;
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
public class UserEntityRepositoryIntegrationTests {

    private final UserRepository underTest;

    @Autowired
    public UserEntityRepositoryIntegrationTests(UserRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatUserCanBeCreated() {
        UserEntity userEntity = TestDataUtil.createTestUserA();
        underTest.save(userEntity);

        Optional<UserEntity> result = underTest.findById(userEntity.getUserID());
        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(userEntity);
    }

    @Test
    public void testThatMultipleUsersCanBeCreated(){

        UserEntity userEntityA = TestDataUtil.createTestUserA();
        underTest.save(userEntityA);

        UserEntity userEntityB = TestDataUtil.createTestUserB();
        underTest.save(userEntityB);

        UserEntity userEntityC = TestDataUtil.createTestUserC();
        underTest.save(userEntityC);

        Iterable<UserEntity> result = underTest.findAll();
        List<UserEntity> expectedCustomerEntities = Arrays.asList(userEntityA, userEntityB, userEntityC);

        assertIterableEquals(expectedCustomerEntities, result);
    }
    @Test
    public void testThatUserCanBeUpdated(){

        UserEntity userEntityA = TestDataUtil.createTestUserA();
        underTest.save(userEntityA);
        userEntityA.setName("Matthew");
        underTest.save(userEntityA);
        Optional<UserEntity> result = underTest.findById(userEntityA.getUserID());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(userEntityA);
    }

    @Test
    public void testThatUserCanBeDeleted(){
        UserEntity userEntityA = TestDataUtil.createTestUserA();
        underTest.save(userEntityA);
        underTest.deleteById(userEntityA.getUserID());
        underTest.save(userEntityA);
        Optional<UserEntity> result = underTest.findById(userEntityA.getUserID());
        assertThat(result).isEmpty();
    }
}

