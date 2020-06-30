package com.myclass.service;

import java.util.List;

import com.myclass.dto.CategoryDTO;

public interface CategoryService {
	public List<CategoryDTO> findAll();
	public CategoryDTO findById(int id);
	public boolean insert(CategoryDTO model);
	public boolean update(CategoryDTO model);
	public boolean delete(int id);
}
