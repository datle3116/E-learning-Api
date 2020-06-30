package com.myclass.repository.impl;

import org.springframework.stereotype.Repository;

import com.myclass.entity.Target;
import com.myclass.repository.TargetRepository;
@Repository
public class TargetRepositoryImpl extends GenericRepositoryImpl<Target, Integer> implements TargetRepository{
}
