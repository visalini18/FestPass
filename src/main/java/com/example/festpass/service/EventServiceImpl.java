package com.example.festpass.service;

import com.example.festpass.entity.FestEvent;
import com.example.festpass.entity.TicketStatus;
import com.example.festpass.exception.EventNotFoundException;
import com.example.festpass.repository.EventRepository;
import com.example.festpass.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public EventServiceImpl(EventRepository eventRepository,
                            TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public FestEvent createEvent(FestEvent event) {
        return eventRepository.save(event);
    }

    @Override
    public List<FestEvent> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public FestEvent getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException(
                                "Event not found with id: " + id));
    }

    @Override
    public FestEvent updateEvent(Long id, FestEvent event) {
        FestEvent existingEvent = getEventById(id);

        existingEvent.setName(event.getName());
        existingEvent.setEventDate(event.getEventDate());
        existingEvent.setVenue(event.getVenue());
        existingEvent.setCapacity(event.getCapacity());
        existingEvent.setTicketPrice(event.getTicketPrice());

        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        FestEvent existingEvent = getEventById(id);
        eventRepository.delete(existingEvent);
    }

    @Override
    public long getHeadcount(Long eventId) {
        getEventById(eventId);

        return ticketRepository.countByEventIdAndStatus(
                eventId,
                TicketStatus.USED
        );
    }
}