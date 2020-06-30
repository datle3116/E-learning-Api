package com.myclass.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CourseDTO {
	private int id;
	private String title;
	private String image;
	private int lectureCount;
	private int hourCount;
	private int viewCount;
	private float price;
	private int discount;
	private float promotionPrice;
	private String description;
	private String content;
	private Timestamp lastUpdate;
	private int categoryId;
	public CourseDTO() {
	}
	public CourseDTO(int id, String title, String image, int lectureCount, int hourCount, int viewCount, float price,
			int discount, float promotionPrice, String description, String content, Timestamp lastUpdate, int categoryId) {
		this.id = id;
		this.title = title;
		this.image = image;
		this.lectureCount = lectureCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.lastUpdate = lastUpdate;
		this.categoryId = categoryId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLectureCount() {
		return lectureCount;
	}
	public void setLectureCount(int lectureCount) {
		this.lectureCount = lectureCount;
	}
	public int getHourCount() {
		return hourCount;
	}
	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public float getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
