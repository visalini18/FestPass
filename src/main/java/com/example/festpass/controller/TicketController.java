package com.example.festpass.controller;

import com.example.festpass.dto.CreateTicketRequest;
import com.example.festpass.dto.QRValidationRequest;
import com.example.festpass.entity.Ticket;
import com.example.festpass.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> purchaseTicket(
            @Valid @RequestBody CreateTicketRequest request) {

        Ticket ticket = ticketService.purchaseTicket(
                request.getEventId(),
                request.getAttendeeId()
        );

        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/validate")
    public ResponseEntity<Ticket> validateTicket(
            @Valid @RequestBody QRValidationRequest request) {

        Ticket ticket = ticketService.validateTicket(
                request.getQrCode()
        );

        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ticketService.getTicketById(id)
        );
    }
}