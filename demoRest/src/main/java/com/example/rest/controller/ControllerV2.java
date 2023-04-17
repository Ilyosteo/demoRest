package com.example.rest.controller;

import com.example.rest.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ControllerV2 {

    private StorageService service;

    @Autowired
    public ControllerV2(StorageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> uploadImages(@RequestParam("image")MultipartFile file) throws Exception {
        String uploadImages = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImages);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
