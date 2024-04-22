package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PhotoshootRepository extends JpaRepository<PhotoshootEntity, UUID> {

    Page<PhotoshootEntity> findAllByPhotographer_userID(UUID photographerId, Pageable pageable);
}
