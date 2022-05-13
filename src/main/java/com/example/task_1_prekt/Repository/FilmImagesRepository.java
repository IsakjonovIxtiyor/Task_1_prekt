package com.example.task_1_prekt.Repository;


import com.example.task_1_prekt.Entity.FilmImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmImagesRepository extends JpaRepository<FilmImage,Long> {
    Optional<FilmImage> findByName(String name);
}
