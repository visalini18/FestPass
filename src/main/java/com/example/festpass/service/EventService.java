package com.example.festpass.service;

import com.example.festpass.entity.FestEvent;

import java.util.List;

public interface EventService {

    FestEvent createEvent(FestEvent event);

    List<FestEvent> getAllEvents();

    FestEvent getEventById(Long id);

    FestEvent updateEvent(Long id, FestEvent event);

    void deleteEvent(Long id);

    long getHeadcount(Long eventId);
}