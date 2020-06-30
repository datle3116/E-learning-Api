package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.VideoDTO;
import com.myclass.entity.Video;
import com.myclass.repository.CourseRepository;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService{
	@Autowired
	VideoRepository videoRepository;
	@Autowired
	CourseRepository courseRepository;

	public List<VideoDTO> findAll()	{
		return listEntityToListDto(videoRepository.findAll());
	}

	public VideoDTO findById(int id) {
		return entityToDto(videoRepository.findById(id));
	}

	public boolean insert(VideoDTO model) {
		return videoRepository.save(dtoToEntity(model));
		
	}

	public boolean update(VideoDTO model) {
		return videoRepository.save(dtoToEntity(model));
		
	}

	public boolean delete(int id) {
		return videoRepository.removeById(id);
		
	}
	private VideoDTO entityToDto(Video entity) {
		VideoDTO dto = new VideoDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setUrl(entity.getUrl());
		dto.setTimeCount(entity.getTimeCount());
		dto.setCourseId(entity.getCourse().getId());
		return dto;
	}
	private Video dtoToEntity(VideoDTO dto) {
		Video entity = new Video();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setUrl(dto.getUrl());
		entity.setCourse(courseRepository.findById(dto.getCourseId()));
		return entity;
	}
	private List<VideoDTO> listEntityToListDto(List<Video> entities){
		List<VideoDTO> dtos = new ArrayList<VideoDTO>();
		for(Video entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}

}
