package com.nik.ecom.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nik.ecom.dto.ProductDetailDto;
import com.nik.ecom.dto.ProductDto;
import com.nik.ecom.entity.FAQ;
import com.nik.ecom.entity.Product;
import com.nik.ecom.entity.Review;
import com.nik.ecom.repository.FAQRepository;
import com.nik.ecom.repository.ProductRepository;
import com.nik.ecom.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{

	private final ProductRepository productRepository;
	
	private final FAQRepository faqRepository;

	private final ReviewRepository reviewRepository;

	public List<ProductDto> getAllProducts(){
		List<Product> products=productRepository.findAll();
		return products.stream().map(Product :: getDto).collect(Collectors.toList());
	}
	
	public List<ProductDto> searchProductByTitle(String name){
		List<Product> products=productRepository.findAllByNameContaining(name);
		return products.stream().map(Product :: getDto).collect(Collectors.toList());
	}
	
	public ProductDetailDto getProductDetailById(Long productId) {
		Optional<Product> optionalProduct=productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			
			List<FAQ> faqList=faqRepository.findAllByProductId(productId);
			List<Review> reviewsList=reviewRepository.findAllByProductId(productId);
			
			ProductDetailDto productDetailDto=new ProductDetailDto();
			productDetailDto.setProductDto(optionalProduct.get().getDto());
			productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
			productDetailDto.setReviewDtoList(reviewsList.stream().map(Review::getDto).collect(Collectors.toList()));
			return productDetailDto;
			
		}
		return null;
	}
}











