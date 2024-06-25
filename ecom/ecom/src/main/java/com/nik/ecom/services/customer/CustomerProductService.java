package com.nik.ecom.services.customer;

import java.util.List;

import com.nik.ecom.dto.ProductDetailDto;
import com.nik.ecom.dto.ProductDto;

public interface CustomerProductService {

	List<ProductDto> getAllProducts();
	
	List<ProductDto> searchProductByTitle(String title);
	
	ProductDetailDto getProductDetailById(Long productId);
}
