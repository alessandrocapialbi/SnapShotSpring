package com.SWE_photoshoot_booking.services;

import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.repositories.UserRepository;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void testSave() {
        UserEntity userEntity = new UserEntity();
        when(repository.save(userEntity)).thenReturn(userEntity);

        UserEntity result = service.save(userEntity);

        assertEquals(userEntity, result);
    }

    @Test
    public void testFindById() {
        UserEntity userEntity = new UserEntity();
        UUID id = UUID.randomUUID();
        userEntity.setUserID(id);
        when(repository.findById(id)).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = service.findById(id);

        assertEquals(Optional.of(userEntity), result);
    }

    @Test
    public void testFindAll() {
        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();
        List<UserEntity> userEntities = Arrays.asList(userEntity1, userEntity2);

        Page<UserEntity> userEntityPage = new PageImpl<>(userEntities);
        Pageable pageable = PageRequest.of(0, 2);

        when(repository.findAll(pageable)).thenReturn(userEntityPage);

        Page<UserEntity> result = service.findAll(pageable);

        assertEquals(userEntityPage, result);
    }

    @Test
    public void testUpdate() {
        UserEntity userEntity = new UserEntity();
        UUID id = UUID.randomUUID();
        userEntity.setUserID(id);
        when(repository.findById(id)).thenReturn(Optional.of(userEntity));

        UserEntity updatedUserEntity = new UserEntity();
        updatedUserEntity.setName("Updated Name");
        when(repository.save(updatedUserEntity)).thenReturn(updatedUserEntity);

        UserEntity result = service.partialUpdate(id, updatedUserEntity);

        assertEquals(updatedUserEntity, result);
    }

    @Test
    public void testDelete() {
        UserEntity userEntity = new UserEntity();
        UUID id = UUID.randomUUID();
        userEntity.setUserID(id);
        when(repository.findById(id)).thenReturn(Optional.of(userEntity));

        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

}