package com.nik.ecom.controller.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.ecom.dto.OrderedProductResponseDto;
import com.nik.ecom.dto.WishlistDto;
import com.nik.ecom.services.customer.wishlist.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class WishlistController {

	private final WishlistService wishlistService;
	
	@PostMapping("/wishlist")
	public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto){
		WishlistDto postedWishlistDto=wishlistService.addProductToWishlist(wishlistDto);
		if(postedWishlistDto ==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something wents wrong");
		return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
		
	}
	
	 @GetMapping("/wishlist/{userId}")
	  public ResponseEntity<List<WishlistDto>> getWishlistByUSerId(@PathVariable Long userId){
		  return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));
	  }
	 
}
