package com.example.task_1_prekt.Payload;

import com.example.task_1_prekt.Entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResFilm {
    private Long id;
    private String filmName;    // film nomi
    private String filmDate;    // film vaqti
    private String location;    // film mazili
    private String filmPrice;   // film narxi
    private Long filmImageId;

    public ResFilm(Film film) {
        this.id = film.getId();
        this.filmName = film.getFilmName();
        this.filmDate = film.getFilmDate();
        this.location = film.getLocation();
        this.filmPrice = film.getFilmPrice();
        this.filmImageId= film.getFilmImage()!=null?film.getFilmImage().getId():null;
    }
}
