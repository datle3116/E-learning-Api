package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.RoleDTO;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserCourseRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	public List<RoleDTO> findAll() {
		return listEntityToListDto(roleRepository.findAll());
	}

	public RoleDTO findById(int id) {
		return entityToDto(roleRepository.findById(id));
	}

	public boolean insert(RoleDTO model) {
		return roleRepository.save(dtoToEntity(model));
		
	}

	public boolean update(RoleDTO model) {
		return roleRepository.save(dtoToEntity(model));
		
	}

	public boolean delete(int id) {
		for(User i : userRepository.findAll()) {
			if(i.getRole().getId() == id) {
				return false;
			}
		}
		return roleRepository.removeById(id);
		
	}
	private RoleDTO entityToDto(Role entity) {
		RoleDTO dto = new RoleDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		return dto;
	}
	private Role dtoToEntity(RoleDTO dto) {
		Role entity = new Role();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		return entity;
	}
	private List<RoleDTO> listEntityToListDto(List<Role> entities){
		List<RoleDTO> dtos = new ArrayList<RoleDTO>();
		for(Role entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}

}
