package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractController<E, D> {

    protected final AbstractCrudService<E> service;
    protected final Mapper<E, D> mapper;

    protected AbstractController(AbstractCrudService<E> service, Mapper<E, D> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto) {
        E entity = mapper.mapFrom(dto);
        E savedEntity = service.create(entity);
        return new ResponseEntity<>(mapper.mapTo(savedEntity), HttpStatus.CREATED);
    }


}