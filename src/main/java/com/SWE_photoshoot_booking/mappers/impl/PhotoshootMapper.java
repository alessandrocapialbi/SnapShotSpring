package com.SWE_photoshoot_booking.mappers.impl;

import com.SWE_photoshoot_booking.domain.dto.PhotoshootDto;
import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PhotoshootMapper implements Mapper<PhotoshootEntity, PhotoshootDto> {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public PhotoshootMapper(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        modelMapper.createTypeMap(PhotoshootEntity.class, PhotoshootDto.class)
                .addMappings(mapper -> mapper.using(ctx ->
                                ((UserEntity) ctx.getSource()).getUserID())
                        .map(PhotoshootEntity::getPhotographer, PhotoshootDto::setPhotographer));
    }

    @Override
    public PhotoshootDto mapTo(PhotoshootEntity photoshootEntity) {
        return modelMapper.map(photoshootEntity, PhotoshootDto.class);
    }

    @Override
    public PhotoshootEntity mapFrom(PhotoshootDto photoshootDto) {
        PhotoshootEntity photoshootEntity = modelMapper.map(photoshootDto, PhotoshootEntity.class);

        UserEntity photographer = userService.findById(photoshootDto.getPhotographer())
                .orElseThrow(() -> new RuntimeException("User not found"));

        photoshootEntity.setPhotographer(photographer);

        return photoshootEntity;
    }
}
