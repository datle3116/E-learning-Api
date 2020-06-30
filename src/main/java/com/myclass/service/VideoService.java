package com.myclass.service;

import java.util.List;

import com.myclass.dto.VideoDTO;

public interface VideoService {
	public List<VideoDTO> findAll();
	public VideoDTO findById(int id);
	public boolean insert(VideoDTO model);
	public boolean update(VideoDTO model);
	public boolean delete(int id);
}
