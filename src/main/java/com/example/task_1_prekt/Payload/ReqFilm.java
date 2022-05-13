package com.example.task_1_prekt.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqFilm {
    private Long id;
    private String filmName;    // film nomi
    private String filmDate;    // film vaqti
    private String location;    // film mazili
    private String filmPrice;   // film narxi
    private Long filmImageId;

}
