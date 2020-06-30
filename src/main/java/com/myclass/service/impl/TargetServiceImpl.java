package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.TargetDTO;
import com.myclass.entity.Target;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.TargetRepository;
import com.myclass.service.TargetService;
@Service
public class TargetServiceImpl implements TargetService{
	@Autowired
	private TargetRepository targetRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	public List<TargetDTO> findAll() {
		return listEntityToListDto(targetRepository.findAll());
	}

	public TargetDTO findById(int id) {
		return entityToDto(targetRepository.findById(id));
	}

	public boolean insert(TargetDTO model) {
		return targetRepository.save(dtoToEntity(model));
		
	}

	public boolean update(TargetDTO model) {
		return targetRepository.save(dtoToEntity(model));
		
	}

	public boolean delete(int id) {
		return targetRepository.removeById(id);
		
	}
	private TargetDTO entityToDto(Target entity) {
		TargetDTO dto = new TargetDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setCourseId(entity.getCourse().getId());
		return dto;
	}
	private Target dtoToEntity(TargetDTO dto) {
		Target entity = new Target();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setCourse(courseRepository.findById(dto.getCourseId()));
		return entity;
	}
	private List<TargetDTO> listEntityToListDto(List<Target> entities){
		List<TargetDTO> dtos = new ArrayList<TargetDTO>();
		for(Target entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}

}
