package com.personal.book.library.datalayer.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.book.library.datalayer.model.LikeDegree;

@Document(collection = "book")
@Entity
@Table(name = "book")
public class Book implements Serializable {

	private static final long serialVersionUID = 7699254600012746828L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length=11, nullable=false)
	private BigInteger id;
	
	@Column(name = "name", length=100, nullable=false)
	private String name;
	
	@Column(name = "author_full_name", length=100, nullable=false)
	private String authorFullName;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category", nullable=false)
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable=false)
	@JsonIgnore
	private User user;
	
	@Column(name = "like_degree", nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private LikeDegree likeDegree;
	
	@Column(name = "created_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Transient
	private BigInteger userId;
	
	@Transient
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
