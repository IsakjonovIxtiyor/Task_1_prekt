package com.example.task_1_prekt.Controller;

import com.example.task_1_prekt.Entity.FilmImage;
import com.example.task_1_prekt.Repository.FilmImagesRepository;
import com.example.task_1_prekt.Util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FilmImageController {

    @Autowired
    private FilmImagesRepository filmImagesRepository;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        filmImagesRepository.save(FilmImage.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = {"/get/image/info/{name}"})
    public FilmImage getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<FilmImage> dbImage = filmImagesRepository.findByName(name);

        return FilmImage.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<FilmImage> dbImage = filmImagesRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
}
