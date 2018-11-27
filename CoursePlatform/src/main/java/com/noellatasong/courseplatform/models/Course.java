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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;



@Entity
@Table(name = "Course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 3, max = 200, message="Course name must be present")
	private String name;
	
	@Size(min = 3, max = 200, message="Instructor name must be present")
	private String instructor;
	
	@Min(1)
	private int capacity;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "signup", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    
    private List<User> users;
    
    public List<User> getUsers(){
    	return users;
    }
    public void setUsers(List<User> users) {
    	this.users = users;
    }
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Course() {
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
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
