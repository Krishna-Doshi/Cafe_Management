package com.cdac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public Optional<Category> findByName(String Name);

	public void deleteById(int categoryId);

	public boolean existsById(int categoryId);

}
