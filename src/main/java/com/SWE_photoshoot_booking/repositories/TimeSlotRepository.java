package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, Long> {
}
