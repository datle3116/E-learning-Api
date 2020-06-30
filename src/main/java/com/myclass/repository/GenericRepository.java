package com.myclass.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T, K> {
	List<T> findAll();
	T findById(K id);
	boolean save(T model);
	boolean removeById(K id);
}
