package com.example.festpass.controller;

import com.example.festpass.dto.CreateEventRequest;
import com.example.festpass.entity.FestEvent;
import com.example.festpass.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<FestEvent> createEvent(
            @Valid @RequestBody CreateEventRequest request) {

        FestEvent event = new FestEvent();

        event.setName(request.getName());
        event.setEventDate(request.getEventDate());
        event.setVenue(request.getVenue());
        event.setCapacity(request.getCapacity());
        event.setTicketPrice(request.getTicketPrice());

        return new ResponseEntity<>(
                eventService.createEvent(event),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<FestEvent>> getAllEvents() {

        return ResponseEntity.ok(
                eventService.getAllEvents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FestEvent> getEventById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                eventService.getEventById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FestEvent> updateEvent(
            @PathVariable Long id,
            @RequestBody FestEvent event) {

        return ResponseEntity.ok(
                eventService.updateEvent(id, event)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long id) {

        eventService.deleteEvent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/headcount")
    public ResponseEntity<Long> getHeadcount(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                eventService.getHeadcount(id)
        );
    }
}