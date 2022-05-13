package com.example.task_1_prekt.Controller;

import com.example.task_1_prekt.Payload.ReqFilm;
import com.example.task_1_prekt.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneFilm(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(filmService.getOne(id));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public HttpEntity<?> EditAndCreateFilm(@RequestBody ReqFilm reqFilm) {
        return ResponseEntity.ok().body(filmService.editAndCreateFilm(reqFilm));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteFilm(@PathVariable Long id) {
        return ResponseEntity.ok().body(filmService.deleteFilm(id));
    }
}
