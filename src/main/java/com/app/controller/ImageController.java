package com.app.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {

    @GetMapping("/images/{subdirectory}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String subdirectory, @PathVariable String filename) throws IOException {
        Path imagePath = Paths.get("C:\\plan_garlic\\images\\" + subdirectory + "\\" + filename);
        byte[] imageBytes = Files.readAllBytes(imagePath);

        MediaType mediaType = MediaTypeFactory.getMediaType(new FileSystemResource(imagePath.toFile()))
                .orElse(MediaType.parseMediaType(MimeTypeUtils.IMAGE_JPEG_VALUE));

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageBytes);
    }
}

