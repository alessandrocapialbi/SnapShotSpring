package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findAllByCustomer_UserID(UUID userID);
}