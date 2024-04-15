package com.SWE_photoshoot_booking.services;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.util.Optional;


public abstract class AbstractCrudService<E, R extends JpaRepository<E, Long>> {


    private final R repository;

    public AbstractCrudService(R repository) {
        this.repository = repository;
    }

    public E save(E entity) {
        return repository.save(entity);
    }

    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public abstract E partialUpdate(Long id, E entity);

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public boolean doesNotExist(Long id) {
        return !repository.existsById(id);
    }

    protected R getRepository() {
        return repository;
    }


}