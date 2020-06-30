package com.myclass.repository.impl;

import org.springframework.stereotype.Repository;

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Role, Integer> implements RoleRepository{
}
