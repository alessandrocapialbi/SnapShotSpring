package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
