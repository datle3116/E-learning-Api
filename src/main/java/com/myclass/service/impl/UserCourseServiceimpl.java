package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserCourseDTO;
import com.myclass.entity.UserCourse;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserCourseRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserCourseService;
@Service
public class UserCourseServiceimpl implements UserCourseService{
	@Autowired
	private UserCourseRepository userCourseRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;

	public List<UserCourseDTO> findAll() {
		return listEntityToListDto(userCourseRepository.findAll());
	}

	public UserCourseDTO findById(int id) {
		return entityToDto(userCourseRepository.findById(id));
	}

	public boolean insert(UserCourseDTO model) {
		return userCourseRepository.insert(dtoToEntity(model));
		
	}

	public boolean update(UserCourseDTO model) {
		return userCourseRepository.update(dtoToEntity(model));
		
	}

	public boolean delete(int id) {
		return userCourseRepository.delete(id);
	}
	private UserCourseDTO entityToDto(UserCourse entity) {
		UserCourseDTO dto = new UserCourseDTO();
		dto.setUserId(entity.getUser().getId());
		dto.setCourseId(entity.getCourse().getId());
		dto.setJoinDate(entity.getJoinDate());
		return dto;
	}
	private UserCourse dtoToEntity(UserCourseDTO dto) {
		UserCourse entity = new UserCourse();
		entity.setUser(userRepository.findById(dto.getUserId()));
		entity.setCourse(courseRepository.findById(dto.getCourseId()));
		entity.setJoinDate(dto.getJoinDate());
		return entity;
	}
	private List<UserCourseDTO> listEntityToListDto(List<UserCourse> entities){
		List<UserCourseDTO> dtos = new ArrayList<UserCourseDTO>();
		for(UserCourse entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}

}
