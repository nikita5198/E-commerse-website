package com.nik.ecom.services.admin.category;

import java.util.List;

import com.nik.ecom.dto.CategoryDto;
import com.nik.ecom.entity.Category;

public interface CategoryService {
	Category createCategory(CategoryDto categoryDto);
	
	List<Category> getAllCategories();
}
