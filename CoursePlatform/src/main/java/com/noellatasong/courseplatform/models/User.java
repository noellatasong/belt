package com.noellatasong.courseplatform.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;



@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 1, max = 200, message="this field is required")
	private String name;
	
	@Size(min = 1, max = 200, message="this field is required")
	private String email;
	
	@Size(min = 8, max = 200, message="Password confirmation must be between 8-64 char.")
	private String password;
	
	@Size(min=8, max=200, message="Password confirmation must be between 8-64 char.")
	private String confirm;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Course> courses;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "signup", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> signedcourse;
	
	public List<Course> getSignedcourse() {
		return signedcourse;
	}

	public void setSignedcourse(List<Course> signedcourse) {
		this.signedcourse = signedcourse;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}	