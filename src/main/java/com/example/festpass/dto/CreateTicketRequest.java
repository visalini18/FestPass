package com.example.festpass.dto;

import jakarta.validation.constraints.NotNull;

public class CreateTicketRequest {

    @NotNull(message = "Event ID is required")
    private Long eventId;

    @NotNull(message = "Attendee ID is required")
    private Long attendeeId;

    public CreateTicketRequest() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(Long attendeeId) {
        this.attendeeId = attendeeId;
    }
}