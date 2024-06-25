package com.nik.ecom.services.customer.cart;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.nik.ecom.dto.AddProductInCartDto;
import com.nik.ecom.dto.OrderDto;
import com.nik.ecom.dto.PlaceOrderDto;

public interface CartService {

	ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	
	OrderDto getCartByUserId(Long userId);
	
	 OrderDto applyCoupon(Long userId,String code);
	 
	 OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
	 
	 OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
	 
	 OrderDto placeOrder(PlaceOrderDto placeOrderDto);
	 
	 List<OrderDto> getMyPlacedOrders(Long userId);
	 
	 OrderDto searchOrderByTrackingId(UUID trackingId);
}
