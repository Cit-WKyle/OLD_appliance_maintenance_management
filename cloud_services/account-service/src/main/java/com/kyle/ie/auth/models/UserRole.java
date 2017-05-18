package com.kyle.ie.auth.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {
	
	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@Column(name="app_user_id")
		protected Long userId;
		
		@Enumerated(EnumType.STRING)
		@Column(name="role")
		protected Role role;
		
		public Id() {}
	
		public Id(Long userId, Role role) {
			this.userId = userId;
			this.role = role;
		}
	}
	
	@EmbeddedId
	Id id = new Id();
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", insertable=false, updatable=false)
	protected Role _role;
	
	public Role getRole() {
		return _role;
	}
	
	public void setRole(Role role) {
		_role = role;
	}
	
	public Id getId() {
		return id;
	}
	
	public void setId(Id id) {
		this.id = id;
	}
}
