package com.imgur.demo.service.impl.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.imgur.demo.service.impl.ImgurServiceImpl;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;

public class ImgurServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ImgurServiceImpl imgurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadImage_Success() throws IOException {
        
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("test.jpg");
        when(mockFile.getResource()).thenReturn(new ByteArrayResource("imageData".getBytes()));

        String url = "https://api.imgur.com/3/upload";
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"success\": true}", HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.POST), any(), eq(String.class)))
            .thenReturn(responseEntity);

      
        String response = imgurService.uploadImage(mockFile);

        // Assert
        assertEquals("{\"success\": true}", response);
    }

    @Test
    void testGetImage_Success() {
     
        String imageId = "testImageId";
        String url = "https://api.imgur.com/3/image/" + imageId;
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"data\": {\"id\": \"testImageId\"}}", HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(), eq(String.class)))
            .thenReturn(responseEntity);

       
        String response = imgurService.getImage(imageId);

        // Assert
        assertEquals("{\"data\": {\"id\": \"testImageId\"}}", response);
    }

    @Test
    void testDeleteImage_Success() {
       
        String imageId = "testImageId";
        String url = "https://api.imgur.com/3/image/" + imageId;
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"success\": true}", HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.DELETE), any(), eq(String.class)))
            .thenReturn(responseEntity);

        
        String response = imgurService.deleteImage(imageId);

        // Assert
        assertEquals("{\"success\": true}", response);
    }

    @Test
    void testUploadImage_Failure() throws IOException {
        
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("test.jpg");
        when(mockFile.getResource()).thenReturn(new ByteArrayResource("imageData".getBytes()));

        String url = "https://api.imgur.com/3/upload";
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"success\": false}", HttpStatus.BAD_REQUEST);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.POST), any(), eq(String.class)))
            .thenReturn(responseEntity);

       
        String response = imgurService.uploadImage(mockFile);

        // Assert
        assertEquals("{\"success\": false}", response);
    }

    @Test
    void testGetImage_NotFound() {
        
        String imageId = "nonExistentImageId";
        String url = "https://api.imgur.com/3/image/" + imageId;
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), any(), eq(String.class)))
            .thenReturn(responseEntity);

       
        String response = imgurService.getImage(imageId);

        // Assert
        assertEquals("Image not found", response);
    }

    @Test
    void testDeleteImage_Failure() {
       
        String imageId = "testImageId";
        String url = "https://api.imgur.com/3/image/" + imageId;
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{\"success\": false}", HttpStatus.BAD_REQUEST);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.DELETE), any(), eq(String.class)))
            .thenReturn(responseEntity);

        
        String response = imgurService.deleteImage(imageId);

        // Assert
        assertEquals("{\"success\": false}", response);
    }
}
