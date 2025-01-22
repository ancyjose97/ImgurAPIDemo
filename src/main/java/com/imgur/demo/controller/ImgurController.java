package com.imgur.demo.controller;

import com.imgur.demo.service.ImgurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImgurController {

    @Autowired
    private ImgurService imgurService;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam MultipartFile file) {
        return imgurService.uploadImage(file);
    }

    @GetMapping("/{imageId}")
    public String getImage(@PathVariable String imageId) {
        return imgurService.getImage(imageId);
    }

    @DeleteMapping("/{imageId}")
    public String deleteImage(@PathVariable String imageId) {
        return imgurService.deleteImage(imageId);
    }
}

