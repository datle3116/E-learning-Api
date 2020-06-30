package com.myclass.repository;

import com.myclass.entity.Course;

public interface CourseRepository extends GenericRepository<Course, Integer>{
	public boolean insert(Course model);
	public boolean update(Course model);
}
