/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */	
package com.miskowskij.app.persistence.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQuery;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Sample user class.
 * 
 * @author Oliver Gierke
 * @author Thomas Darimont
 */
@Entity
@NamedQuery(name = "User.findByTheUsersName", query = "from User u where u.username = ?1")
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -2952735933715107252L;
    
    @CreatedDate
    private Date creationTime;

	@Column(unique = true) 
	private String username;
	
	private String firstname;
	private String lastname;
	
    @LastModifiedDate
    private Date modificationTime;

	public User() {
		this(null);     
	}

	/**
	 * Creates a new user instance.
	 */
	public User(Long id) {
		this.setId(id);
	}

	public Date getCreationTime() {
		return creationTime;
	}
	
	/**
	 * Returns the username.
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getModificationTime() {
		return modificationTime;
	}
	
}