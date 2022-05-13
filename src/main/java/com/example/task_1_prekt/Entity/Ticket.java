package com.example.task_1_prekt.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filmName;       // korsatuv nomi
    private String filmDate;         // korsatuv vaqti
    private String ticketPrice;    // chipta narxi
    private String ticketStatus;   // chipta Xolati
    private Long row;              //qator
    @Column(unique = true,nullable = false)
    private Long rowColumn;           //qatordagiOrindiq

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;


}
