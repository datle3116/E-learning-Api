package com.myclass.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.myclass.repository.GenericRepository;

@Transactional(rollbackOn = Exception.class)
public abstract class GenericRepositoryImpl<T, K> implements GenericRepository<T, K> {
	@Autowired
	protected SessionFactory sessionFactory;

	protected Class<? extends T> clazz;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericRepositoryImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		clazz = (Class) pt.getActualTypeArguments()[0];
	}

	public List<T> findAll() {
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			@SuppressWarnings("unchecked")
			CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery(clazz);
			@SuppressWarnings("unchecked")
			Root<T> root = (Root<T>) query.from(clazz);
			query.select(root);
			Query<T> q = session.createQuery(query);
			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public T findById(K id) {
		Session session = sessionFactory.openSession();
		try {
			return session.find(clazz, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public boolean save(T model) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(model);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean removeById(K id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			T model = session.find(clazz, id);
			if(model != null) {
				session.remove(model);
				transaction.commit();
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}
}
