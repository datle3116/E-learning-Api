package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDTO;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserCourseRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserCourseRepository userCourseRepository;

	public List<UserDTO> findAll() {
		return listEntityToListDto(userRepository.findAll());
	}

	public UserDTO findById(int id) {
		return entityToDto(userRepository.findById(id));
	}

	public boolean insert(UserDTO model) {
		return userRepository.save(dtoToEntity(model));
		
	}

	public boolean update(UserDTO model) {
		return userRepository.save(dtoToEntity(model));
		
	}

	public boolean delete(int id) {
		for(UserCourse i : userCourseRepository.findAll()) {
			if(i.getUser().getId() == id) {
				return false;
			}
		}
		return userRepository.removeById(id);
		
	}
	private UserDTO entityToDto(User entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setEmail(entity.getEmail());
		dto.setFullname(entity.getFullname());
		dto.setAvatar(entity.getAvatar());
		dto.setPhone(entity.getPhone());
		dto.setAddress(entity.getAddress());
		dto.setRoleId(entity.getRole().getId());
		return dto;
	}
	private User dtoToEntity(UserDTO dto) {
		User entity = new User();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setFullname(dto.getFullname());
		entity.setPassword(dto.getPassword());
		entity.setAvatar(dto.getAvatar());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		entity.setRole(roleRepository.findById(dto.getRoleId()));
		return entity;
	}
	private List<UserDTO> listEntityToListDto(List<User> entities){
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for(User entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}

}
