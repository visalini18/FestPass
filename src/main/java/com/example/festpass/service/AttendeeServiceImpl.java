package com.example.festpass.service;

import com.example.festpass.entity.Attendee;
import com.example.festpass.exception.AttendeeNotFoundException;
import com.example.festpass.repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public Attendee createAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    @Override
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    @Override
    public Attendee getAttendeeById(Long id) {
        return attendeeRepository.findById(id)
                .orElseThrow(() ->
                        new AttendeeNotFoundException(
                                "Attendee not found with id: " + id));
    }

    @Override
    public Attendee updateAttendee(Long id, Attendee attendee) {
        Attendee existingAttendee = getAttendeeById(id);

        existingAttendee.setName(attendee.getName());
        existingAttendee.setEmail(attendee.getEmail());
        existingAttendee.setPhone(attendee.getPhone());

        return attendeeRepository.save(existingAttendee);
    }

    @Override
    public void deleteAttendee(Long id) {
        Attendee existingAttendee = getAttendeeById(id);
        attendeeRepository.delete(existingAttendee);
    }
}