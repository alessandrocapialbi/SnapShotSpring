package com.SWE_photoshoot_booking.repositories;

import org.springframework.data.repository.CrudRepository;
import com.SWE_photoshoot_booking.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


}
