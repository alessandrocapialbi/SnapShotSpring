package com.SWE_photoshoot_booking.controllers.impl;

import com.SWE_photoshoot_booking.controllers.AbstractController;
import com.SWE_photoshoot_booking.domain.dto.UserDto;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.repositories.UserRepository;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<UserEntity, UserDto, UserRepository> {


    @Autowired
    public UserController(UserService userService, Mapper<UserEntity, UserDto> customerMapper) {
        super(userService, customerMapper);
    }


}
