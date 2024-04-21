package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.dto.IdentifiableDto;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


public abstract class AbstractController<E, D extends IdentifiableDto, R extends JpaRepository<E, UUID>> {

    protected final AbstractCrudService<E, R> service;
    protected final Mapper<E, D> mapper;

    protected AbstractController(AbstractCrudService<E, R> service, Mapper<E, D> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto) {
        E entity = mapper.mapFrom(dto);
        E savedEntity = service.save(entity);
        return new ResponseEntity<>(mapper.mapTo(savedEntity), HttpStatus.CREATED);
    }


    @GetMapping
    public Page<D> listRecords(Pageable pageable) {
        Page<E> listAll = service.findAll(pageable);
        return listAll.map(mapper::mapTo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getRecord(@PathVariable("id") UUID id) {
        Optional<E> foundEntity = service.findById(id);
        return foundEntity.map(E -> {
            D dto = mapper.mapTo(E);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> fullUpdateRecord(@PathVariable("id") UUID id, @RequestBody D dto) {
        if (service.doesNotExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dto.setId(id);
        E entity = mapper.mapFrom(dto);
        E savedEntity = service.save(entity);
        return new ResponseEntity<>(mapper.mapTo(savedEntity), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<D> partialUpdateRecord(@PathVariable("id") UUID id, @RequestBody D dto) {
        if (service.doesNotExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        E entity = mapper.mapFrom(dto);
        E updatedEntity = service.partialUpdate(id, entity);
        return new ResponseEntity<>(mapper.mapTo(updatedEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}