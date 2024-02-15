package com.cdac.repository;

import java.util.List;

import com.cdac.entity.Category;
import com.cdac.entity.Product;


public interface CategoryServiceRepository {

    void addCategory(Category category);

   
    List<Category> getAllCategories();
    Category getCategoryById(int categoryId);
    void addProductToCategory(int categoryId, Product product);
}
