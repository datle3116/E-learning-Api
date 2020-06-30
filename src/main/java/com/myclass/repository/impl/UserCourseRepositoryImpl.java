package com.myclass.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclass.entity.UserCourse;
import com.myclass.repository.UserCourseRepository;
@Repository
@Transactional(rollbackOn = Exception.class)
public class UserCourseRepositoryImpl implements UserCourseRepository{
	@Autowired
	private SessionFactory sessionFactory;

	public boolean delete(Integer id) {
		String hql = "DELETE FROM UserCourse WHERE userId = :id OR courseId = :id";
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<UserCourse> query = session.createQuery(hql);
			query.setParameter("id", id);
			query.executeUpdate();
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

	public List<UserCourse> findAll() {
		String hql = "FROM UserCourse";
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<UserCourse> query = session.createQuery(hql);
			return query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public UserCourse findById(Integer id) {
		String hql = "FROM UserCourse WHERE userId = :id OR courseId = :id";
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<UserCourse> query = session.createQuery(hql);
			query.setParameter("id", id);
			query.executeUpdate();
			return query.getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public boolean insert(UserCourse model) {
		String hql = "INSERT INTO user_courses (user_id, course_id, join_date) VALUES (:user_id, :course_id, now())";
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<UserCourse> query = session.createSQLQuery(hql);
			query.setParameter("user_id", model.getUser().getId());
			query.setParameter("course_id", model.getCourse().getId());
			query.executeUpdate();
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
	public boolean update(UserCourse model) {
		String hql = "UPDATE UserCourse SET join_date = now() WHERE user_id = :user_id AND course_id = :course_id";
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<UserCourse> query = session.createQuery(hql);
			query.setParameter("user_id", model.getUser().getId());
			query.setParameter("course_id", model.getCourse().getId());
			query.executeUpdate();
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
}
