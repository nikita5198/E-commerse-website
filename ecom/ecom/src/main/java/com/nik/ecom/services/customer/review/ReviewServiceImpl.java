package com.nik.ecom.services.customer.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nik.ecom.dto.OrderedProductResponseDto;
import com.nik.ecom.dto.ProductDto;
import com.nik.ecom.dto.ReviewDto;
import com.nik.ecom.entity.CartItems;
import com.nik.ecom.entity.Order;
import com.nik.ecom.entity.Product;
import com.nik.ecom.entity.Review;
import com.nik.ecom.entity.User;
import com.nik.ecom.repository.OrderRepository;
import com.nik.ecom.repository.ProductRepository;
import com.nik.ecom.repository.ReviewRepository;
import com.nik.ecom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final OrderRepository orderRepository;
	
	private final UserRepository userRepository;

	private final ProductRepository productRepository;

	private final ReviewRepository reviewRepository;

	
	public OrderedProductResponseDto getOrderedProductsDetailsByOrderId(Long orderId) {
		Optional<Order> optionalOrder=orderRepository.findById(orderId);
		OrderedProductResponseDto orderedProductResponseDto =new OrderedProductResponseDto();
		
		if(optionalOrder.isPresent()) {
			orderedProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());
			
			List<ProductDto> productDtoList=new ArrayList<>();
			
			for(CartItems cartItems:optionalOrder.get().getCartItems()) {
				ProductDto productDto=new ProductDto();
				productDto.setId(cartItems.getProduct().getId());
				productDto.setName(cartItems.getProduct().getName());
				productDto.setPrice(cartItems.getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				productDto.setByteImg(cartItems.getProduct().getImg());
				
				productDtoList.add(productDto);
			}
			orderedProductResponseDto.setProductDtoList(productDtoList);
		}
		return orderedProductResponseDto;
	}
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
		Optional<Product> optionalProduct=productRepository.findById(reviewDto.getProductId());
		Optional<User> optionalUser=userRepository.findById(reviewDto.getUserId());
		if(optionalProduct.isPresent() && optionalUser.isPresent()) {
			Review review =new Review();
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setUser(optionalUser.get());
			review.setProduct(optionalProduct.get());
			review.setImg(reviewDto.getImg().getBytes());
			
			return reviewRepository.save(review).getDto();
		}
		return null;

	}
	
}
