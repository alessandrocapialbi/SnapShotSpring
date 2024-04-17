package com.SWE_photoshoot_booking.controllers.auth;

import com.SWE_photoshoot_booking.domain.dto.UserDto;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.mappers.impl.UserMapper;
import com.SWE_photoshoot_booking.services.AuthenticationService;
import com.SWE_photoshoot_booking.services.impl.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    private final UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    public RegistrationController(AuthenticationService authenticationService, UserService userService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "/register";
    }


    @PostMapping("/register/user")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        UserEntity existingCustomer = userService.findCustomerByEmail(userMapper.mapFrom(userDto).getEmail());

        if (existingCustomer != null && existingCustomer.getEmail() != null && !existingCustomer.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }


        log.info("Registering customer with email: {}", userDto.getEmail());
        authenticationService.registerUser(userMapper.mapFrom(userDto));
        log.info("User registered successfully");
        return "redirect:/register?success";
    }

}