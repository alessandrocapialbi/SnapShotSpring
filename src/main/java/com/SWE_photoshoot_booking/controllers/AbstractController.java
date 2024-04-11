package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractController<E, D, R extends CrudRepository<E, Long>> {

    protected final AbstractCrudService<E, R> service;
    protected final Mapper<E, D> mapper;

    protected AbstractController(AbstractCrudService<E, R> service, Mapper<E, D> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto) {
        E entity = mapper.mapFrom(dto);
        E savedEntity = service.create(entity);
        return new ResponseEntity<>(mapper.mapTo(savedEntity), HttpStatus.CREATED);
    }


    @GetMapping
    public List<D> listRecords() {
       List<E> listAll = service.findAll();
       return listAll.stream().map(mapper::mapTo).collect(Collectors.toList());
    }
}