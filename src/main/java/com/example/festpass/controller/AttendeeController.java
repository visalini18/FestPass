package com.example.festpass.controller;

import com.example.festpass.entity.Attendee;
import com.example.festpass.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @PostMapping
    public ResponseEntity<Attendee> createAttendee(
            @RequestBody Attendee attendee) {

        return new ResponseEntity<>(
                attendeeService.createAttendee(attendee),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Attendee>> getAllAttendees() {
        return ResponseEntity.ok(
                attendeeService.getAllAttendees()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendeeService.getAttendeeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendee> updateAttendee(
            @PathVariable Long id,
            @RequestBody Attendee attendee) {

        return ResponseEntity.ok(
                attendeeService.updateAttendee(id, attendee)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendee(
            @PathVariable Long id) {

        attendeeService.deleteAttendee(id);

        return ResponseEntity.noContent().build();
    }
}