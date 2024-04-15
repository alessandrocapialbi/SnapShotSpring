package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.dto.IdentifiableDto;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractController<E, D extends IdentifiableDto, R extends CrudRepository<E, Long>> {

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
    public List<D> listRecords() {
        List<E> listAll = service.findAll();
        return listAll.stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getRecord(@PathVariable("id") Long id) {
        Optional<E> foundEntity = service.findById(id);
        return foundEntity.map(E -> {
            D dto = mapper.mapTo(E);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> fullUpdateRecord(@PathVariable("id") Long id, @RequestBody D dto) {
        if (service.doesNotExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        dto.setId(id);
        E entity = mapper.mapFrom(dto);
        E savedEntity = service.save(entity);
        return new ResponseEntity<>(mapper.mapTo(savedEntity), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<D> partialUpdateRecord(@PathVariable("id") Long id, @RequestBody D dto) {
        if (service.doesNotExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        E entity = mapper.mapFrom(dto);
        E updatedEntity = service.partialUpdate(id, entity);
        return new ResponseEntity<>(mapper.mapTo(updatedEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}