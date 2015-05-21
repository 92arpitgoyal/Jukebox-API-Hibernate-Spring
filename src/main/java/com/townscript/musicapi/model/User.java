package com.townscript.musicapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

@Proxy(lazy=false)
@Entity
@Table(name = "USERS")

@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="user")
public class User {

	@Id @GeneratedValue
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "USER_NAME")
	private String name;

	@Column(name = "USER_EMAIL")
	private String email;

	@Column(name = "USER_PASSWORD")
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}