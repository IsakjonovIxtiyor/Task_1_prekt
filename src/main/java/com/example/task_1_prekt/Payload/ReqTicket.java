package com.example.task_1_prekt.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqTicket {
    private Long id;

    private String filmName;       // korsatuv nomi
    private String filmDate;         // korsatuv vaqti
    private String ticketPrice;    // chipta narxi
    private String ticketStatus;   // chipta Xolati
    private Long row;              //qator
    private Long rowColumn;           //qatordagiOrindiq

}
