package com.example.task_1_prekt.Repository;

import com.example.task_1_prekt.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
