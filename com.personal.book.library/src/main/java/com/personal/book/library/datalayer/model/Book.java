package com.personal.book.library.datalayer.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.personal.book.library.datalayer.entity.Category;
import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.datalayer.model.LikeDegree;

@Document(collection = "book")
public class Book implements Serializable {

	private static final long serialVersionUID = 7699254600012746828L;

	@org.springframework.data.annotation.Id
	private BigInteger id;
	
	private String name;
	
	private String authorFullName;
	
	private Category category;
	
	private User user;
	
	private LikeDegree likeDegree;
	
	private Date createdDate;
	
	private BigInteger userId;
		
	private BigInteger mongoDbId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorFullName() {
		return authorFullName;
	}

	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = authorFullName;
	}

	public LikeDegree getLikeDegree() {
		return likeDegree;
	}

	public void setLikeDegree(LikeDegree likeDegree) {
		this.likeDegree = likeDegree;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getMongoDbId() {
		return mongoDbId;
	}

	public void setMongoDbId(BigInteger mongoDbId) {
		this.mongoDbId = mongoDbId;
	}
}
