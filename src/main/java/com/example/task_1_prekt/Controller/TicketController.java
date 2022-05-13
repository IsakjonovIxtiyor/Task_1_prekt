package com.example.task_1_prekt.Controller;

import com.example.task_1_prekt.Payload.ReqFilm;
import com.example.task_1_prekt.Payload.ReqTicket;
import com.example.task_1_prekt.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneTicket(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(ticketService.getOne(id));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping()
    public HttpEntity<?> EditAndCreateTicket(@RequestBody ReqTicket reqTicket) {
        return ResponseEntity.ok().body(ticketService.editAndCreateTicket(reqTicket));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTicket(@PathVariable Long id) {
        return ResponseEntity.ok().body(ticketService.deleteTicket(id));
    }

}
