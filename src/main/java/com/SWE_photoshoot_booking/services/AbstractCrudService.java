package com.SWE_photoshoot_booking.services;

import java.util.List;

public interface AbstractCrudService<E> {

    E create(E entity);

    List<E> findAll();
}