package com.maathru.backend.Application.controllers.v1;

import com.maathru.backend.External.utils.UploadImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/upload")
@Slf4j
public class ImageController {

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) {
        try {
            String fileName = UploadImage.upload(file);
            // Access url -> http://localhost:8081/images/<image-name>
            return ResponseEntity.ok("http://localhost:8081/images/" + fileName);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image upload failed");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

