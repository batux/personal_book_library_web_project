package com.personal.book.library.datalayer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -3624018922530401503L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length=11, nullable=false)
	private Long id;
	
	@Column(name = "name", length=100, nullable=false)
	private String name;
	
	@Column(name = "surname", length=100, nullable=false)
	private String surname;
	
	@Column(name = "nick_name", length=25, nullable=false)
	private String nickName;
	
	@Column(name = "password", length=100, nullable=false)
	private String password;
	
	@Column(name = "created_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date createDate;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
