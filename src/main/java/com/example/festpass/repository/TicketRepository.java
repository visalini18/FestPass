package com.example.festpass.repository;

import com.example.festpass.entity.Ticket;
import com.example.festpass.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByQrCode(String qrCode);

    long countByEventId(Long eventId);

    long countByEventIdAndStatus(Long eventId, TicketStatus status);
}