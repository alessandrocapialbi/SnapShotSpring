package com.SWE_photoshoot_booking.services;

import com.SWE_photoshoot_booking.domain.entities.TimeSlotEntity;
import com.SWE_photoshoot_booking.repositories.TimeSlotRepository;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Component
public class TimeSlotResetTask {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotResetTask(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Task executed everyday at midnight
    public void resetBookedTimeSlots() {
        int yesterdayDayOfWeek = LocalDate.now().minusDays(1).getDayOfWeek().getValue();
        Pageable pageable = PageRequest.of(0, 10);
        Page<TimeSlotEntity> timeSlots = timeSlotRepository.findByDayOfWeek(yesterdayDayOfWeek, pageable);
        for (TimeSlotEntity timeSlot : timeSlots) {
            timeSlot.setBooked(false);
            timeSlotRepository.save(timeSlot);
        }
    }
}