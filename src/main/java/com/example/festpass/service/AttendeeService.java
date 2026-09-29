package com.example.festpass.service;

import com.example.festpass.entity.Attendee;

import java.util.List;

public interface AttendeeService {

    Attendee createAttendee(Attendee attendee);

    List<Attendee> getAllAttendees();

    Attendee getAttendeeById(Long id);

    Attendee updateAttendee(Long id, Attendee attendee);

    void deleteAttendee(Long id);
}