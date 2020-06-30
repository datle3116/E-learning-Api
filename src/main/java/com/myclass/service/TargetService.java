package com.myclass.service;

import java.util.List;

import com.myclass.dto.TargetDTO;


public interface TargetService {
	public List<TargetDTO> findAll();
	public TargetDTO findById(int id);
	public boolean insert(TargetDTO model);
	public boolean update(TargetDTO model);
	public boolean delete(int id);
}
