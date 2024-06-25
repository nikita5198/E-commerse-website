package com.nik.ecom.dto;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import com.nik.ecom.entity.Product;
import com.nik.ecom.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
public class ReviewDto {

	private Long id;
	
	private Long rating;
	
	private String description;
	
	private MultipartFile img;
	
	private byte[] returnedImg;
	
	private Long userId;
	
	private Long productId;
	
	private String userName;
}
