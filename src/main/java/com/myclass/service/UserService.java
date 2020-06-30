package com.myclass.service;

import java.util.List;

import com.myclass.dto.UserDTO;


public interface UserService {
	public List<UserDTO> findAll();
	public UserDTO findById(int id);
	public boolean insert(UserDTO model);
	public boolean update(UserDTO model);
	public boolean delete(int id);
}
