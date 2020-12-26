package com.talissonmelo.projectevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talissonmelo.projectevent.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
