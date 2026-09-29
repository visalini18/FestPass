package com.example.festpass.service;

import com.example.festpass.entity.Ticket;

public interface TicketService {

    Ticket purchaseTicket(Long eventId, Long attendeeId);

    Ticket validateTicket(String qrCode);

    Ticket getTicketById(Long id);
}