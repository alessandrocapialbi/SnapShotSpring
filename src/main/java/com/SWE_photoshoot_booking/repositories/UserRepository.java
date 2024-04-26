package com.SWE_photoshoot_booking.repositories;

import com.SWE_photoshoot_booking.domain.entities.Role;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Page<UserEntity> findAllByRole(Role role, Pageable pageable);
}
