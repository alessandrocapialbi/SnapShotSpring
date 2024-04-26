package com.SWE_photoshoot_booking.services.impl;

import com.SWE_photoshoot_booking.domain.entities.AppointmentEntity;
import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.repositories.TimeSlotRepository;
import com.SWE_photoshoot_booking.services.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TimeSlotService extends AbstractCrudService<TimeSlotEntity, TimeSlotRepository> {


    AppointmentService appointmentService;

    @Autowired
    public TimeSlotService(TimeSlotRepository timeSlotRepository, AppointmentService appointmentService) {
        super(timeSlotRepository);
        this.appointmentService = appointmentService;
    }

    @Override
    public TimeSlotEntity partialUpdate(UUID id, TimeSlotEntity entity) {
        entity.setTimeslotID(id);
        return getRepository().findById(id).map(existingTimeSlot -> {
            Optional.ofNullable(entity.getDayOfWeek()).ifPresent(existingTimeSlot::setDayOfWeek);
            Optional.ofNullable(entity.getStartTime()).ifPresent(existingTimeSlot::setStartTime);
            Optional.ofNullable(entity.getEndTime()).ifPresent(existingTimeSlot::setEndTime);
            Optional.of(entity.isBooked()).ifPresent(existingTimeSlot::setBooked);
            return getRepository().save(existingTimeSlot);
        }).orElseThrow(() -> new RuntimeException("Time Slot not exists"));
    }

    @Override
    public Page<TimeSlotEntity> findAllById(UUID id, Pageable pageable) {
        return getRepository().findAllByPhotographer_userID(id, pageable);
    }

    public void checkIfBookedAppointment(UUID id) {
        Optional<AppointmentEntity> appointment = appointmentService.findByTimeSlot(id);
        appointment.ifPresent(appointmentEntity -> appointmentService.deleteById(appointmentEntity.getAppointmentID()));
    }
}