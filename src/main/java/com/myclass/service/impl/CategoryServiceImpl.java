package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.CategoryDTO;
import com.myclass.entity.Category;
import com.myclass.entity.Course;
import com.myclass.repository.CategoryRepository;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CourseRepository courseRepository;

	public List<CategoryDTO> findAll() {
		return listEntityToListDto(categoryRepository.findAll());
	}

	public CategoryDTO findById(int id) {
		return entityToDto(categoryRepository.findById(id));
	}

	public boolean insert(CategoryDTO model) {
		return categoryRepository.save(dtoToEntity(model));
		
	}

	public boolean update(CategoryDTO model) {
		return categoryRepository.save(dtoToEntity(model));
	}

	public boolean delete(int id) {
		for(Course i : courseRepository.findAll()) {
			if(i.getCategory().getId() == id) {
				return false;
			}
		}
		return categoryRepository.removeById(id);
		
	}
	private CategoryDTO entityToDto(Category entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setIcon(entity.getIcon());
		return dto;
	}
	private Category dtoToEntity(CategoryDTO dto) {
		Category entity = new Category();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setIcon(dto.getIcon());
		return entity;
	}
	private List<CategoryDTO> listEntityToListDto(List<Category> entities){
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		for(Category entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}

}
