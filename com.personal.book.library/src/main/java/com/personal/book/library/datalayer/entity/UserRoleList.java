package com.personal.book.library.datalayer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "user_role_list")
public class UserRoleList implements Serializable{

	private static final long serialVersionUID = -8802029677122828184L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length=11, nullable=false)
	private Long id;
	
	@Column(name = "user_id", length=11, nullable=false)
	private Long userId;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private UserRole userRole;
	
	@Column(name = "status", nullable=false)
	private Boolean status = false;
	
	@Column(name = "priority", nullable=false)
	private Byte priority = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Byte getPriority() {
		return priority;
	}

	public void setPriority(Byte priority) {
		this.priority = priority;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
