package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.CourseDTO;
import com.myclass.entity.Course;
import com.myclass.entity.Target;
import com.myclass.entity.UserCourse;
import com.myclass.entity.Video;
import com.myclass.repository.CategoryRepository;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.TargetRepository;
import com.myclass.repository.UserCourseRepository;
import com.myclass.repository.VideoRepository;
import com.myclass.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private TargetRepository targetRepository;
	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private UserCourseRepository userCourseRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	public List<CourseDTO> findAll() {
		return listEntityToListDto(courseRepository.findAll());
	}

	public CourseDTO findById(int id) {
		return entityToDto(courseRepository.findById(id));
	}

	public boolean insert(CourseDTO model) {
		return courseRepository.insert(dtoToEntity(model));
	}

	public boolean update(CourseDTO model) {
		return courseRepository.update(dtoToEntity(model));
		
	}

	public boolean delete(int id) {
		for(Target i : targetRepository.findAll()) {
			if(i.getCourse().getId() == id) {
				return false;
			}
		}
		for(Video i : videoRepository.findAll()) {
			if(i.getCourse().getId() == id) {
				return false;
			}
		}
		for(UserCourse i : userCourseRepository.findAll()) {
			if(i.getCourse().getId() == id) {
				return false;
			}
		}
		return courseRepository.removeById(id);
		
	}
	private CourseDTO entityToDto(Course entity) {
		CourseDTO dto = new CourseDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setLectureCount(entity.getLectureCount());
		dto.setHourCount(entity.getHourCount());
		dto.setViewCount(entity.getViewCount());
		dto.setDiscount(entity.getDiscount());
		dto.setPromotionPrice(entity.getPromotionPrice());
		dto.setDescription(entity.getDescription());
		dto.setContent(entity.getContent());
		dto.setLastUpdate(entity.getLastUpdate());
		dto.setCategoryId(entity.getCategory().getId());
		return dto;
	}
	private Course dtoToEntity(CourseDTO dto) {
		Course entity = new Course();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setImage(dto.getImage());
		entity.setLectureCount(dto.getLectureCount());
		entity.setHourCount(dto.getHourCount());
		entity.setViewCount(dto.getViewCount());
		entity.setDiscount(dto.getDiscount());
		entity.setPromotionPrice(dto.getPromotionPrice());
		entity.setDescription(dto.getDescription());
		entity.setContent(dto.getContent());
		entity.setLastUpdate(dto.getLastUpdate());
		entity.setCategory(categoryRepository.findById(dto.getCategoryId()));
		return entity;
	}
	private List<CourseDTO> listEntityToListDto(List<Course> entities){
		List<CourseDTO> dtos = new ArrayList<CourseDTO>();
		for(Course entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}

}
