package com.SWE_photoshoot_booking.services;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


public abstract class AbstractCrudService<E, R extends JpaRepository<E, UUID>> {


    private final R repository;

    public AbstractCrudService(R repository) {
        this.repository = repository;
    }

    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }


    @Transactional(readOnly = true)
    public Optional<E> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public abstract E partialUpdate(UUID id, E entity);

    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<E> findAllById(UUID id, Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    public boolean doesNotExist(UUID id) {
        return !repository.existsById(id);
    }

    protected R getRepository() {
        return repository;
    }


}