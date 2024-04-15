package com.SWE_photoshoot_booking.mappers.impl;

import com.SWE_photoshoot_booking.domain.dto.PhotographerDto;
import com.SWE_photoshoot_booking.domain.entities.PhotographerEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PhotographerMapper implements Mapper<PhotographerEntity, PhotographerDto> {

    private final ModelMapper modelMapper;

    public PhotographerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public PhotographerDto mapTo(PhotographerEntity photographerEntity) {
        return modelMapper.map(photographerEntity, PhotographerDto.class);
    }

    @Override
    public PhotographerEntity mapFrom(PhotographerDto photographerDto) {
        return modelMapper.map(photographerDto, PhotographerEntity.class);
    }
}
