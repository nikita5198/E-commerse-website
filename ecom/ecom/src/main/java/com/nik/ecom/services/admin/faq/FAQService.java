package com.nik.ecom.services.admin.faq;

import com.nik.ecom.dto.FAQDto;

public interface FAQService {
	FAQDto postFAQ(Long productId,FAQDto faqDto);
}
