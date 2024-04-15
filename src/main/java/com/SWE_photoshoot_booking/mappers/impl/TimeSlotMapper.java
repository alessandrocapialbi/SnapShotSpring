package com.SWE_photoshoot_booking.mappers.impl;

import com.SWE_photoshoot_booking.domain.dto.TimeSlotDto;
import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TimeSlotMapper implements Mapper<TimeSlotEntity, TimeSlotDto> {

    private final ModelMapper modelMapper;

    public TimeSlotMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TimeSlotDto mapTo(TimeSlotEntity timeSlotEntity) {
        return modelMapper.map(timeSlotEntity, TimeSlotDto.class);
    }

    @Override
    public TimeSlotEntity mapFrom(TimeSlotDto timeSlotDto) {
        return modelMapper.map(timeSlotDto, TimeSlotEntity.class);
    }
}
