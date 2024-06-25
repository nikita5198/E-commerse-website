package com.nik.ecom.services.customer.wishlist;

import java.util.List;

import com.nik.ecom.dto.WishlistDto;

public interface WishlistService {

	 WishlistDto addProductToWishlist(WishlistDto wishlistDto);
	 
	 List<WishlistDto> getWishlistByUserId(Long userId);
}
