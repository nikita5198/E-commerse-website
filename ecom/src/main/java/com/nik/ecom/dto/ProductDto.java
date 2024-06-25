package com.nik.ecom.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ProductDto {

	private Long Id;
	
	private String name;
	
	private Long price;
	
	private String description;
	
	private String categoryName;
	
	private byte[] byteImg;
	
	private Long categoryId;
	
	private MultipartFile img;
	
	private Long quantity;
}
