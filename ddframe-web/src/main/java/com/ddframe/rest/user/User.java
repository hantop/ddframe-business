package com.ddframe.rest.user;

import java.io.Serializable;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.domains.LongId;

@Entity
public class User extends LongId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;

	public User() {
	}

	public User(Long id) {
		setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}
