package com.imgur.demo.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import com.imgur.demo.service.ImgurService;

@Service
public class ImgurServiceImpl implements ImgurService {

	@Value("${imgur.client-id}")
    private String clientId;

    private final RestTemplate restTemplate;
    
    private static final Logger logger = LoggerFactory.getLogger(ImgurServiceImpl.class);

    public ImgurServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Upload Image
    public String uploadImage(MultipartFile file) {
    	
    	logger.info("Uploading image: {}", file.getOriginalFilename());
    	
        String url = "https://api.imgur.com/3/upload";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Client-ID " + clientId);
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("image", file.getResource());

        HttpEntity<?> entity = new HttpEntity<>(bodyBuilder.build(), headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        
        return response.getBody();
    }

    // View Image
    public String getImage(String imageId) {
    	
    	 logger.info("Fetching image with ID: {}", imageId);
    	
        String url = "https://api.imgur.com/3/image/" + imageId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Client-ID " + clientId);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        
        return response.getBody();
    }

    // Delete Image
    public String deleteImage(String imageId) {
    	
    	 logger.info("Deleting image with ID: {}", imageId);
    	 
        String url = "https://api.imgur.com/3/image/" + imageId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Client-ID " + clientId);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        
        return response.getBody();
    }

}




