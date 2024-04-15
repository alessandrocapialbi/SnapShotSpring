package com.SWE_photoshoot_booking.mappers.impl;

import com.SWE_photoshoot_booking.domain.dto.AppointmentDto;
import com.SWE_photoshoot_booking.domain.entities.AppointmentEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper implements Mapper<AppointmentEntity, AppointmentDto> {

    private final ModelMapper modelMapper;

    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AppointmentDto mapTo(AppointmentEntity appointmentEntity) {
        return modelMapper.map(appointmentEntity, AppointmentDto.class);
    }

    @Override
    public AppointmentEntity mapFrom(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, AppointmentEntity.class);
    }
}