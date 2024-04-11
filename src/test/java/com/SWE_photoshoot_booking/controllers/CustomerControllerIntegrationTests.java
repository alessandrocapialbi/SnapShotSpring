package com.SWE_photoshoot_booking.controllers;

import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.repositories.TestDataUtil;
import com.SWE_photoshoot_booking.services.impl.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final CustomerService customerService;


    @Autowired
    public CustomerControllerIntegrationTests(MockMvc mockMvc, CustomerService customerService) {
        this.mockMvc = mockMvc;
        this.customerService = customerService;
        this.objectMapper = new ObjectMapper();
    }


    @Test
    public void testThatCreateCustomerSuccessfullyReturn201() throws Exception {
        CustomerEntity testCustomerA = TestDataUtil.createTestCustomerA();
        testCustomerA.setCustomerID(null);
        String customerJSON = objectMapper.writeValueAsString(testCustomerA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJSON)).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void testThatCreateCustomerSuccessfullyReturnCustomer() throws Exception {
        CustomerEntity testCustomerA = TestDataUtil.createTestCustomerA();
        testCustomerA.setCustomerID(null);
        String customerJSON = objectMapper.writeValueAsString(testCustomerA);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerJSON)).andExpect(
                        MockMvcResultMatchers.jsonPath("$.customerID").isNumber()).
                andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jack"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Sparrow"));


    }

    @Test
    public void testThatListCustomersReturnsHttpStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListCustomersReturnsListOfCustomers() throws Exception {
        CustomerEntity testCustomerEntityA = TestDataUtil.createTestCustomerA();
        customerService.save(testCustomerEntityA);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].customerID").isNumber()).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Jack"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("Sparrow"));
    }


    @Test
    public void testThatGetCustomerReturnsHttpStatus200WhenAuthorExist() throws Exception {
        CustomerEntity testCustomerEntityA = TestDataUtil.createTestCustomerA();
        customerService.save(testCustomerEntityA);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testThatGetCustomerReturnsHttpStatus404WhenNotAuthorExist() throws Exception {
        CustomerEntity testCustomerEntityA = TestDataUtil.createTestCustomerA();
        customerService.save(testCustomerEntityA);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetCustomerReturnsCustomerWhenExists() throws Exception {
        CustomerEntity testCustomerEntityA = TestDataUtil.createTestCustomerA();
        customerService.save(testCustomerEntityA);
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.customerID").value(1)).
                andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jack"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Sparrow"));
    }


}
