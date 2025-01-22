package com.imgur.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImgurService {
	
	String uploadImage(MultipartFile file);
	String getImage(String imageId) ;
	String deleteImage(String imageId);
}
