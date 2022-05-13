package com.example.task_1_prekt.Service;

import com.example.task_1_prekt.Payload.ApiResponse;
import com.example.task_1_prekt.Payload.ReqFilm;
import com.example.task_1_prekt.Payload.ResFilm;

public interface FilmServiceInterface {
    public ResFilm getOne(Long id);
    public ApiResponse editAndCreateFilm(ReqFilm reqFilm);
    public ApiResponse deleteFilm(Long id);
}
