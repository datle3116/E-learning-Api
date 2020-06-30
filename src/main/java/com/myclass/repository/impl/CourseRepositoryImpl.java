package com.myclass.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Course;
import com.myclass.repository.CourseRepository;
@Repository
@Transactional(rollbackOn = Exception.class)
public class CourseRepositoryImpl extends GenericRepositoryImpl<Course, Integer> implements CourseRepository{

	public boolean update(Course model) {
		String hql = "UPDATE Course SET title = :title,"
				+ " image = :image,"
				+ " lectures_count = :lectures_count,"
				+ " hour_count = :hour_count,"
				+ " view_count = :view_count,"
				+ " price = :price,"
				+ " discount = :discount,"
				+ " promotion_price = :promotion_price,"
				+ " description = :description,"
				+ " content = :content,"
				+ " last_update = now(),"
				+ " category_id = :category_id WHERE id = :id";
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<Course> query = session.createQuery(hql);
			query.setParameter("title", model.getTitle());
			query.setParameter("image", model.getImage());
			query.setParameter("lectures_count", model.getLectureCount());
			query.setParameter("hour_count", model.getHourCount());
			query.setParameter("view_count", model.getViewCount());
			query.setParameter("price", model.getPrice());
			query.setParameter("discount", model.getDiscount());
			query.setParameter("promotion_price", model.getPromotionPrice());
			query.setParameter("description", model.getDescription());
			query.setParameter("content", model.getContent());
			query.setParameter("category_id", model.getCategory().getId());
			query.setParameter("id", model.getId());
			
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

	public boolean insert(Course model) {
		String hql = "INSERT INTO courses (title, image, lectures_count, "
				+ "hour_count, view_count, price, discount, promotion_price, description, content, last_update, category_id) "
				+ "VALUES (:title, :image, :lectures_count, "
				+ ":hour_count, :view_count, :price, :discount, :promotion_price, :description, :content, now(), :category_id)";
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<Course> query = session.createSQLQuery(hql);
			query.setParameter("title", model.getTitle());
			query.setParameter("image", model.getImage());
			query.setParameter("lectures_count", model.getLectureCount());
			query.setParameter("hour_count", model.getHourCount());
			query.setParameter("view_count", model.getViewCount());
			query.setParameter("price", model.getPrice());
			query.setParameter("discount", model.getDiscount());
			query.setParameter("promotion_price", model.getPromotionPrice());
			query.setParameter("description", model.getDescription());
			query.setParameter("content", model.getContent());
			query.setParameter("category_id", model.getCategory().getId());
			
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
