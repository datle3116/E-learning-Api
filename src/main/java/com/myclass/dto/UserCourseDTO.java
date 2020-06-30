package com.myclass.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class UserCourseDTO {
	private int userId;
	private int courseId;
	private Timestamp joinDate;
	public UserCourseDTO() {
	}
	public UserCourseDTO(int userId, int courseId, Timestamp joinDate) {
		this.userId = userId;
		this.courseId = courseId;
		this.joinDate = joinDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public Timestamp getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
	
}
