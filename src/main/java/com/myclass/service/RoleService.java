package com.myclass.service;

import java.util.List;

import com.myclass.dto.RoleDTO;


public interface RoleService {
	public List<RoleDTO> findAll();
	public RoleDTO findById(int id);
	public boolean insert(RoleDTO model);
	public boolean update(RoleDTO model);
	public boolean delete(int id);
}
