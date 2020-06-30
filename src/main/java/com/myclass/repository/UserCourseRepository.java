package com.myclass.repository;

import java.util.List;

import com.myclass.entity.UserCourse;

public interface UserCourseRepository{
	public List<UserCourse> findAll();
	public UserCourse findById(Integer id);
	public boolean insert(UserCourse model);
	public boolean update(UserCourse model);
	public boolean delete(Integer id);
}
