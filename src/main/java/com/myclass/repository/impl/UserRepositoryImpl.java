package com.myclass.repository.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User, Integer> implements UserRepository{
	@Autowired
	SessionFactory sessionFactory;

	public User findByEmail(String email) {
		String hql = "FROM users WHERE email = :email";
		Session session = sessionFactory.openSession();
		try {
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
}
