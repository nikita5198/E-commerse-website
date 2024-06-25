package com.nik.ecom.services.admin.faq;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nik.ecom.dto.FAQDto;
import com.nik.ecom.entity.FAQ;
import com.nik.ecom.entity.Product;
import com.nik.ecom.repository.FAQRepository;
import com.nik.ecom.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

	private final FAQRepository faqRepository;
	
	private final ProductRepository productRepository;
	
	public FAQDto postFAQ(Long productId,FAQDto faqDto) {
		Optional<Product> optionalProduct=productRepository.findById(productId);
		if(optionalProduct.isPresent()) {
			FAQ faq=new FAQ();
			faq.setQuestion(faqDto.getQuestion());
			faq.setAnswer(faqDto.getAnswer());
			faq.setProduct(optionalProduct.get());
			return faqRepository.save(faq).getFAQDto();
		}
		return null;
	}
}
