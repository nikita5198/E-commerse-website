package com.nik.ecom.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.nik.ecom.entity.CartItems;
import com.nik.ecom.entity.User;
import com.nik.ecom.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Data
public class OrderDto {

	private Long id;
	
	private String orderDescription;
	
	private Date date;
	
	private Long amount;
	
	private String address;
	
	private String payment;
	
	private OrderStatus orderStatus;
	
	private Long totalAmount;
	
	private Long discount;
	
	private UUID trackingId;
	
	private String userName;
	
	private List<CartItemsDto> cartItems;
	
	private String couponName;
	
}
