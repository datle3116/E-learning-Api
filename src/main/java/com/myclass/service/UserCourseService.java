package com.myclass.service;

import java.util.List;

import com.myclass.dto.UserCourseDTO;


public interface UserCourseService {
	public List<UserCourseDTO> findAll();
	public UserCourseDTO findById(int id);
	public boolean insert(UserCourseDTO model);
	public boolean update(UserCourseDTO model);
	public boolean delete(int id);
}
