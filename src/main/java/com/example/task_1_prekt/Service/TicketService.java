package com.example.task_1_prekt.Service;


import com.example.task_1_prekt.Entity.Ticket;
import com.example.task_1_prekt.Payload.ApiResponse;
import com.example.task_1_prekt.Payload.ReqTicket;
import com.example.task_1_prekt.Payload.ResTicket;
import com.example.task_1_prekt.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService  implements TicketServiceInterface{
    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public ResTicket getOne(Long id) {
        Optional<Ticket> byId = ticketRepository.findById(id);
        if (byId.isPresent()){
            Ticket ticket =byId.get();
            return new ResTicket(ticket);
        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }

    @Override
    public ApiResponse editAndCreateTicket(ReqTicket reqTicket) {
        try {
           Ticket ticket = new Ticket();
            if (reqTicket.getId() != null) {
                ticketRepository.findById(reqTicket.getId()).orElseThrow(() ->
                        new IllegalStateException("Region with this id not found"));
            }
            ticket.setFilmName(reqTicket.getFilmName());
            ticket.setFilmDate(reqTicket.getFilmDate());
            ticket.setTicketPrice(reqTicket.getTicketPrice());
            ticket.setTicketStatus(reqTicket.getTicketStatus());
            ticket.setRow(reqTicket.getRow());
            ticket.setRowColumn(reqTicket.getRowColumn());
            ticketRepository.save(ticket);
            return new ApiResponse(reqTicket.getId() != null ? "Edit" : "Save", true);
        } catch (Exception e) {
            return new ApiResponse("Boshqa joy toping!!!", false);
        }
    }

    @Override
    public ApiResponse deleteTicket(Long id) {
        Optional<Ticket> byId = ticketRepository.findById(id);
        if (byId.isPresent()){
            ticketRepository.deleteById(id);

            return new ApiResponse("Ticket o`chirildi",true);

        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }
    public List<Ticket> listAll() {
        return ticketRepository.findAll(Sort.by("id").ascending());
    }
}
