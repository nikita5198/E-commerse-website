package com.nik.ecom.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.nik.ecom.dto.CartItemsDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class CartItems {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long price;
	
	private Long quantity;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="product_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@ToString.Exclude
	private Product product;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="user_id", nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@ToString.Exclude
	private User user;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	@ToString.Exclude
	private Order order;
	
	public CartItemsDto getCartDto() {
		CartItemsDto cartItemsDto=new CartItemsDto();
		cartItemsDto.setId(id);
		cartItemsDto.setPrice(price);
		cartItemsDto.setProductId(product.getId());
		cartItemsDto.setQuantity(quantity);
		cartItemsDto.setUserId(user.getId());
		cartItemsDto.setProductName(product.getName());
		cartItemsDto.setReturnedImg(product.getImg());
		return cartItemsDto;
	}
}















