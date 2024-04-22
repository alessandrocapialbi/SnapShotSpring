package com.SWE_photoshoot_booking.services;

import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public abstract class AbstractCrudService<E, R extends JpaRepository<E, UUID>> {


    private final R repository;

    public AbstractCrudService(R repository) {
        this.repository = repository;
    }

    public E save(E entity) {
        return repository.save(entity);
    }

    public Optional<E> findById(UUID id) {
        return repository.findById(id);
    }

    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public abstract E partialUpdate(UUID id, E entity);

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public Page<E> findAllById(UUID id, Pageable pageable) {
        return null;
    }

    public boolean doesNotExist(UUID id) {
        return !repository.existsById(id);
    }

    public UserEntity findByEmail(String email) {
        return ((UserRepository) repository).findByEmail(email);
    }

    protected R getRepository() {
        return repository;
    }


}