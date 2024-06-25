package com.nik.ecom.services.customer.review;

import java.io.IOException;

import com.nik.ecom.dto.OrderedProductResponseDto;
import com.nik.ecom.dto.ReviewDto;

public interface ReviewService {

	OrderedProductResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
	ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
