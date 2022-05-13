package com.example.task_1_prekt.Repository;

import com.example.task_1_prekt.Entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
}
