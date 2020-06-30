package com.myclass.repository;

import com.myclass.entity.User;

public interface UserRepository extends GenericRepository<User, Integer>{
	public User findByEmail(String email);
}
