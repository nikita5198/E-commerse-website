package com.nik.ecom.services.customer.wishlist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nik.ecom.dto.WishlistDto;
import com.nik.ecom.entity.Product;
import com.nik.ecom.entity.User;
import com.nik.ecom.entity.Wishlist;
import com.nik.ecom.repository.ProductRepository;
import com.nik.ecom.repository.UserRepository;
import com.nik.ecom.repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

	private final UserRepository userRepository;

	private final ProductRepository productRepository;
	
	private final WishlistRepository wishlistRepository;
	
	public WishlistDto addProductToWishlist(WishlistDto wishlistDto) {
		Optional<Product> optionalProduct=productRepository.findById(wishlistDto.getProductId());
		Optional<User> optionalUser=userRepository.findById(wishlistDto.getUserId());
		if(optionalProduct.isPresent() && optionalUser.isPresent()) {
			Wishlist wishlist=new Wishlist();
			wishlist.setProduct(optionalProduct.get());
			wishlist.setUser(optionalUser.get());
			return wishlistRepository.save(wishlist).getWishlistdto();
		}
		return null;
	}

	public List<WishlistDto> getWishlistByUserId(Long userId){
		return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist::getWishlistdto).collect(Collectors.toList());
		
	}
}
