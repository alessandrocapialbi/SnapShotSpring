package com.SWE_photoshoot_booking.mappers.impl;

import com.SWE_photoshoot_booking.domain.dto.AppointmentDto;
import com.SWE_photoshoot_booking.domain.entities.AppointmentEntity;
import com.SWE_photoshoot_booking.domain.entities.PhotoshootEntity;
import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.domain.entities.UserEntity;
import com.SWE_photoshoot_booking.mappers.Mapper;
import com.SWE_photoshoot_booking.services.impl.PhotoshootService;
import com.SWE_photoshoot_booking.services.impl.TimeSlotService;
import com.SWE_photoshoot_booking.services.impl.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper implements Mapper<AppointmentEntity, AppointmentDto> {

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final PhotoshootService photoshootService;

    private final TimeSlotService timeSlotService;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentMapper.class);

    public AppointmentMapper(ModelMapper modelMapper, UserService userService, PhotoshootService photoshootService, TimeSlotService timeSlotService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.photoshootService = photoshootService;
        this.timeSlotService = timeSlotService;

        modelMapper.createTypeMap(AppointmentEntity.class, AppointmentDto.class)
                .addMappings(mapper -> mapper.using(ctx ->
                                ((UserEntity) ctx.getSource()).getUserID())
                        .map(AppointmentEntity::getCustomer, AppointmentDto::setCustomer))
                .addMappings(mapper -> mapper.using(ctx ->
                                ((PhotoshootEntity) ctx.getSource()).getPhotoshootID())
                        .map(AppointmentEntity::getPhotoshootEntity, AppointmentDto::setPhotoshootEntity))
                .addMappings(mapper -> mapper.using(ctx ->
                                ((TimeSlotEntity) ctx.getSource()).getTimeslotID())
                        .map(AppointmentEntity::getTimeslot, AppointmentDto::setTimeslot))
                .addMappings(mapper -> mapper.using(ctx ->
                                ((UserEntity) ctx.getSource()).getUserID())
                        .map(AppointmentEntity::getPhotographer, AppointmentDto::setPhotographer));
    }


    @Override
    public AppointmentDto mapTo(AppointmentEntity appointmentEntity) {
        return modelMapper.map(appointmentEntity, AppointmentDto.class);
    }

    @Override
    public AppointmentEntity mapFrom(AppointmentDto appointmentDto) {
        logger.info("Mapping appointmentDto to appointmentEntity");
        AppointmentEntity appointmentEntity = modelMapper.map(appointmentDto, AppointmentEntity.class);

        UserEntity customer = userService.findById((appointmentDto.getCustomer()))
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        logger.info("Customer found: " + customer.getName());
        appointmentEntity.setCustomer(customer);

        UserEntity photographer = userService.findById((appointmentDto.getPhotographer()))
                .orElseThrow(() -> new RuntimeException("Photographer not found"));
        appointmentEntity.setPhotographer(photographer);

        PhotoshootEntity photoshoot = photoshootService.findById((appointmentDto.getPhotoshootEntity()))
                .orElseThrow(() -> new RuntimeException("Photoshoot not found"));
        appointmentEntity.setPhotoshootEntity(photoshoot);

        TimeSlotEntity timeslot = timeSlotService.findById((appointmentDto.getTimeslot()))
                .orElseThrow(() -> new RuntimeException("Timeslot not found"));
        appointmentEntity.setTimeslot(timeslot);

        return appointmentEntity;
    }
}