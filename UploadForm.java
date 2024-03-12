package com.example.demo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;



@Data
public class UploadForm {
	private MultipartFile multipartFile;	 
}
