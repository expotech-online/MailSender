package org.ahmedukamel.mailesender.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class ImageController {
    @GetMapping(value = "image/{imageName}")
    public ResponseEntity<byte[]> readResource(@PathVariable(value = "imageName") String imageName) throws IOException {
        Resource resource = new ClassPathResource("static/shared-images/" + imageName);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}
