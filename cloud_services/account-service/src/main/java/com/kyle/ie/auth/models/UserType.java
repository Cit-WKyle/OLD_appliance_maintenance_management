package com.kyle.ie.auth.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_types")
public class UserType {

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	
	@Column(name="user_type")
	private String _type;
	
	@JsonIgnore
	public Long getId() {
		return _id;
	}
	
	public void setId(Long id) {
		_id = id;
	}
	
	public String getType() {
		return _type;
	}
	
	public void setType(String type) {
		_type = type;
	}
	
}
