package com.myclass.service;

import java.util.List;

import com.myclass.dto.CourseDTO;


public interface CourseService {
	public List<CourseDTO> findAll();
	public CourseDTO findById(int id);
	public boolean insert(CourseDTO model);
	public boolean update(CourseDTO model);
	public boolean delete(int id);
}
