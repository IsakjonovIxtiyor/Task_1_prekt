package com.example.task_1_prekt.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqHoll {
    private Long id;

    private String Zal;
    private Long row;            //qator
    private Long placePrice;     //orindiq narxi
    private String placeStatus;  // orindiq holati
}
