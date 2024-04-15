package com.SWE_photoshoot_booking.mappers.impl;

import com.SWE_photoshoot_booking.domain.dto.PhotoshootDto;
import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PhotoshootMapper implements Mapper<PhotoshootEntity, PhotoshootDto> {

    private final ModelMapper modelMapper;

    public PhotoshootMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PhotoshootDto mapTo(PhotoshootEntity photoshootEntity) {
        return modelMapper.map(photoshootEntity, PhotoshootDto.class);
    }

    @Override
    public PhotoshootEntity mapFrom(PhotoshootDto photoshootDto) {
        return modelMapper.map(photoshootDto, PhotoshootEntity.class);
    }
}
