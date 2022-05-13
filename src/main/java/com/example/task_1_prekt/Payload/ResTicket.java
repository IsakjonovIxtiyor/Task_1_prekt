package com.example.task_1_prekt.Payload;

import com.example.task_1_prekt.Entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResTicket {
    private Long id;

    private String filmName;       // korsatuv nomi
    private String filmDate;         // korsatuv vaqti
    private String ticketPrice;    // chipta narxi
    private String ticketStatus;   // chipta Xolati
    private Long row;              //qator
    private Long rowColumn;           //qatordagiOrindiq

    public ResTicket(Ticket ticket) {
        this.id = ticket.getId();
        this.filmName = ticket.getFilmName();
        this.filmDate = ticket.getFilmDate();
        this.ticketPrice = ticket.getTicketPrice();
        this.ticketStatus = ticket.getTicketStatus();
        this.row = ticket.getRow();
        this.rowColumn = ticket.getRowColumn();
    }
}
