package com.example.festpass.repository;

import com.example.festpass.entity.FestEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<FestEvent, Long> {
}