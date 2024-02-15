package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.entity.Category;
import com.cdac.entity.Product;
import com.cdac.repository.CategoryRepository;
import com.cdac.repository.CategoryServiceRepository;
import com.cdac.repository.ProductRepository;

@Service
@Transactional
public class CategoryService implements CategoryServiceRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public void addProductToCategory(int categoryId, Product product) {
        Category category = getCategoryById(categoryId);
        if (category != null) {
            product.setCategory(category);
            productRepository.save(product);
        }
  
    }
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public boolean existsById(int categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    public void updateCategory(int categoryId, Category updatedCategory) {
        Optional<Category> existingCategoryOptional = categoryRepository.findById(categoryId);

        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            existingCategory.setName(updatedCategory.getName());
            existingCategory.setDescription(updatedCategory.getDescription());

            categoryRepository.save(existingCategory);
        }
        
    }
}
    


