package com.SWE_photoshoot_booking.mappers.impl;

import com.SWE_photoshoot_booking.domain.dto.TimeSlotDto;
import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TimeSlotMapper implements Mapper<TimeSlotEntity, TimeSlotDto> {

    private final ModelMapper modelMapper;

    private final UserService userService;

    public TimeSlotMapper(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        modelMapper.createTypeMap(TimeSlotEntity.class, TimeSlotDto.class)
                .addMappings(mapper -> mapper.using(ctx ->
                                ((UserEntity) ctx.getSource()).getUserID())
                        .map(TimeSlotEntity::getPhotographer, TimeSlotDto::setPhotographer));
    }

    @Override
    public TimeSlotDto mapTo(TimeSlotEntity timeSlotEntity) {
        return modelMapper.map(timeSlotEntity, TimeSlotDto.class);
    }


    @Override
    public TimeSlotEntity mapFrom(TimeSlotDto timeSlotDto) {
        TimeSlotEntity timeSlotEntity = modelMapper.map(timeSlotDto, TimeSlotEntity.class);

        UserEntity photographer = userService.findById(timeSlotDto.getPhotographer())
                .orElseThrow(() -> new RuntimeException("User not found"));

        timeSlotEntity.setPhotographer(photographer);

        return timeSlotEntity;
    }

}
