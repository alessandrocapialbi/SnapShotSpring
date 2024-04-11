package com.SWE_photoshoot_booking.services;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class AbstractCrudService<E, R extends CrudRepository<E, Long>> {


    private final R repository;

    public AbstractCrudService(R repository) {
        this.repository = repository;
    }

    public E create(E entity) {
        return repository.save(entity);
    }

    public List<E> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }
}