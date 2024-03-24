package com.SWE_photoshoot_booking.mappers.impl;

import com.SWE_photoshoot_booking.domain.dto.CustomerDto;
import com.SWE_photoshoot_booking.domain.entities.CustomerEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<CustomerEntity, CustomerDto> {

    private ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public CustomerDto mapTo(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    @Override
    public CustomerEntity mapFrom(CustomerDto customerDto) {
        return modelMapper.map(customerDto, CustomerEntity.class);
    }
}
