package com.example.festpass.service;

import com.example.festpass.entity.Attendee;
import com.example.festpass.entity.FestEvent;
import com.example.festpass.entity.Ticket;
import com.example.festpass.entity.TicketStatus;
import com.example.festpass.exception.AttendeeNotFoundException;
import com.example.festpass.exception.EventCapacityExceededException;
import com.example.festpass.exception.EventNotFoundException;
import com.example.festpass.exception.TicketAlreadyUsedException;
import com.example.festpass.exception.TicketNotFoundException;
import com.example.festpass.repository.AttendeeRepository;
import com.example.festpass.repository.EventRepository;
import com.example.festpass.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             EventRepository eventRepository,
                             AttendeeRepository attendeeRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public Ticket purchaseTicket(Long eventId, Long attendeeId) {

        FestEvent event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new EventNotFoundException(
                                "Event not found with id: " + eventId));

        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() ->
                        new AttendeeNotFoundException(
                                "Attendee not found with id: " + attendeeId));

        long soldTickets = ticketRepository.countByEventId(eventId);

        if (soldTickets >= event.getCapacity()) {
            throw new EventCapacityExceededException(
                    "Event capacity has been reached");
        }

        Ticket ticket = new Ticket();

        ticket.setQrCode(UUID.randomUUID().toString());
        ticket.setStatus(TicketStatus.ISSUED);
        ticket.setIssuedAt(LocalDateTime.now());
        ticket.setPrice(event.getTicketPrice());
        ticket.setEvent(event);
        ticket.setAttendee(attendee);

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket validateTicket(String qrCode) {

        Ticket ticket = ticketRepository.findByQrCode(qrCode)
                .orElseThrow(() ->
                        new TicketNotFoundException(
                                "Invalid QR code"));

        if (ticket.getStatus() == TicketStatus.USED) {
            throw new TicketAlreadyUsedException(
                    "Ticket has already been used");
        }

        ticket.markAsUsed();

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {

        return ticketRepository.findById(id)
                .orElseThrow(() ->
                        new TicketNotFoundException(
                                "Ticket not found with id: " + id));
    }
}