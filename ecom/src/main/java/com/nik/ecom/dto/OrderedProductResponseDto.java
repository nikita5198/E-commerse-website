package com.nik.ecom.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderedProductResponseDto {

	private List<ProductDto> productDtoList;
	
	private Long orderAmount;
}
