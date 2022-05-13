package com.example.task_1_prekt.Payload;

import com.example.task_1_prekt.Entity.Holl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResHoll {
    private Long id;

    private String Zal;
    private Long row;            //qator
    private Long placePrice;     //orindiq narxi
    private String placeStatus;  // orindiq holati

    public ResHoll(Holl holl) {
        this.id = holl.getId();
        Zal = holl.getZal();
        this.row = holl.getRow();
        this.placePrice = holl.getPlacePrice();
        this.placeStatus = holl.getPlaceStatus();
    }
}
