package com.example.task_1_prekt.Service;

import com.example.task_1_prekt.Entity.Film;
import com.example.task_1_prekt.Payload.ApiResponse;
import com.example.task_1_prekt.Payload.ReqFilm;
import com.example.task_1_prekt.Payload.ResFilm;
import com.example.task_1_prekt.Repository.FilmImagesRepository;
import com.example.task_1_prekt.Repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class FilmService implements FilmServiceInterface{
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmImagesRepository filmImagesRepository;

    @Override
    public ResFilm getOne(Long id) {
        Optional<Film> byId = filmRepository.findById(id);
        if (byId.isPresent()){
         Film film =byId.get();
         return new ResFilm(film);
        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }

    @Override
    public ApiResponse editAndCreateFilm(ReqFilm reqFilm) {
        try {
            Film film = new Film();
            if (reqFilm.getId() != null) {
                filmRepository.findById(reqFilm.getId()).orElseThrow(() ->
                        new IllegalStateException("Region with this id not found"));
            }
            if(reqFilm.getFilmImageId()!=null){
                film.setFilmImage(filmImagesRepository.findById(reqFilm.getFilmImageId()).orElseThrow(() -> new IllegalStateException("de")));
            }
            film.setFilmName(reqFilm.getFilmName());
            film.setFilmDate(reqFilm.getFilmDate());
            film.setLocation(reqFilm.getLocation());
            film.setFilmPrice(reqFilm.getFilmPrice());
            filmRepository.save(film);
            return new ApiResponse(reqFilm.getId() != null ? "Edit" : "Save", true);
        } catch (Exception e) {
            return new ApiResponse("Id yoq", false);
        }
    }

    @Override
    public ApiResponse deleteFilm(Long id) {
        Optional<Film> byId = filmRepository.findById(id);
        if (byId.isPresent()){
               filmRepository.deleteById(id);

               return new ApiResponse("Film o`chirildi",true);

        }else{
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }
}
