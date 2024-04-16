package com.SWE_photoshoot_booking.controllers.auth;

import com.SWE_photoshoot_booking.domain.dto.CustomerDto;
import com.SWE_photoshoot_booking.domain.dto.PhotographerDto;
import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.mappers.impl.CustomerMapper;
import com.SWE_photoshoot_booking.mappers.impl.PhotographerMapper;
import com.SWE_photoshoot_booking.services.AuthenticationService;
import com.SWE_photoshoot_booking.services.impl.CustomerService;
import com.SWE_photoshoot_booking.services.impl.PhotographerService;
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

    private final CustomerService customerService;

    private final PhotographerService photographerService;

    private final CustomerMapper customerMapper;

    private final PhotographerMapper photographerMapper;

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    public RegistrationController(AuthenticationService authenticationService, CustomerService customerService, PhotographerService photographerService, CustomerMapper customerMapper, PhotographerMapper photographerMapper) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
        this.photographerService = photographerService;
        this.customerMapper = customerMapper;
        this.photographerMapper = photographerMapper;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new CustomerDto());
        model.addAttribute("photographer", new PhotographerDto());
        return "register";
    }


    @PostMapping("/register/customer")
    public String registerCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto,
                                   BindingResult result,
                                   Model model) {
        CustomerEntity existingCustomer = customerService.findCustomerByEmail(customerMapper.mapFrom(customerDto).getEmail());

        if (existingCustomer != null && existingCustomer.getEmail() != null && !existingCustomer.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("customer", customerDto);
            return "/register";
        }



        log.info("Registering customer with email: {}", customerDto.getEmail());
        authenticationService.registerCustomer(customerMapper.mapFrom(customerDto));
        log.info("Customer registered successfully");
        return "redirect:/register?success";
    }

    @PostMapping("/register/photographer")
    public String registerPhotographer(@Valid @ModelAttribute("photographer") PhotographerDto photographerDto,
                                       BindingResult result,
                                       Model model) {
        PhotographerEntity existingPhotographer = photographerService.findPhotographerByEmail(photographerMapper.mapFrom(photographerDto).getEmail());

        if (existingPhotographer != null && existingPhotographer.getEmail() != null && !existingPhotographer.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("photographer", photographerDto);
            return "/register";
        }

        authenticationService.registerPhotographer(photographerMapper.mapFrom(photographerDto));
        return "redirect:/register?success";
    }
}