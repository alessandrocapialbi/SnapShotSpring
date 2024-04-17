package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.dto.UserDto;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.repositories.TestDataUtil;
import com.SWE_photoshoot_booking.services.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@WithMockUser
public class UserControllerIntegrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final UserService userService;


    @Autowired
    public UserControllerIntegrationTests(MockMvc mockMvc, UserService userService) {
        this.mockMvc = mockMvc;
        this.userService = userService;
        this.objectMapper = new ObjectMapper();
    }


    @Test
    public void testThatCreateUserSuccessfullyReturn201() throws Exception {
        UserEntity testCustomerA = TestDataUtil.createTestUserA();
        testCustomerA.setUserID(null);
        String customerJSON = objectMapper.writeValueAsString(testCustomerA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(customerJSON)).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testThatCreateUserSuccessfullyReturnUser() throws Exception {
        UserEntity testCustomerA = TestDataUtil.createTestUserA();
        testCustomerA.setUserID(null);
        String customerJSON = objectMapper.writeValueAsString(testCustomerA);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .content(customerJSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.userID").isNumber()).
                andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jack"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Sparrow"));


    }

    @Test
    public void testThatListUsersReturnsHttpStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListUsersReturnsListOfUsers() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserA();
        userService.save(testUserEntityA);
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.content[0].userID").isNumber()).
                andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Jack"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].surname").value("Sparrow"));
    }


    @Test
    public void testThatGetUserReturnsHttpStatus200WhenUserExist() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserA();
        userService.save(testUserEntityA);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testThatGetUserReturnsHttpStatus404WhenNoUserExist() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserA();
        userService.save(testUserEntityA);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatFullUpdateUserReturnsHttpStatus404WhenUserNotExists() throws Exception {
        UserDto testUserDtoA = TestDataUtil.createTestUserDtoA();
        String customerDtoJson = objectMapper.writeValueAsString(testUserDtoA);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(customerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatFullUpdateUserReturnsHttpStatus200WhenUserExists() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserA();
        UserEntity savedCustomer = userService.save(testUserEntityA);
        UserDto testUserDtoA = TestDataUtil.createTestUserDtoA();
        String customerDtoJson = objectMapper.writeValueAsString(testUserDtoA);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + savedCustomer.getUserID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(customerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdateUpdatesExistingUser() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserA();
        UserEntity savedCustomer = userService.save(testUserEntityA);
        UserEntity customerDto = TestDataUtil.createTestUserB();
        customerDto.setUserID(savedCustomer.getUserID());
        String customerDtoUpdateJson = objectMapper.writeValueAsString(customerDto);
        objectMapper.writeValueAsString(customerDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + savedCustomer.getUserID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(customerDtoUpdateJson))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.userID").value(savedCustomer.getUserID())).
                andExpect(MockMvcResultMatchers.jsonPath("$.name").value(customerDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(customerDto.getSurname()));
    }


    @Test
    public void testThatPartialUpdateExistingUserReturnsHttpStatus200Ok() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserA();
        UserEntity savedCustomer = userService.save(testUserEntityA);

        UserDto testUserDtoA = TestDataUtil.createTestUserDtoA();
        testUserDtoA.setName("UPDATED");
        String customerDtoJson = objectMapper.writeValueAsString(testUserDtoA);
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/" + savedCustomer.getUserID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(customerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateExistingUserReturnsUpdateUser() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserA();
        UserEntity savedCustomer = userService.save(testUserEntityA);

        UserDto testUserDtoA = TestDataUtil.createTestUserDtoA();
        testUserDtoA.setName("UPDATED");
        String customerDtoJson = objectMapper.writeValueAsString(testUserDtoA);
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/" + savedCustomer.getUserID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(customerDtoJson))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.userID").value(savedCustomer.getUserID())).
                andExpect(MockMvcResultMatchers.jsonPath("$.name").value("UPDATED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value(testUserDtoA.getSurname()));

    }


    @Test
    public void testThatDeleteUserReturnsHttpStatus204ForNonExistingUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteUserReturnsHttpStatus204ForExistingUser() throws Exception {
        UserEntity testUserEntityA = TestDataUtil.createTestUserA();
        UserEntity savedCustomer = userService.save(testUserEntityA);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + savedCustomer.getUserID())
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
