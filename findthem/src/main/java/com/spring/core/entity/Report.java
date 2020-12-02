package com.spring.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="report")
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_report")
	private int id_report;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user_id;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="post_id")
	private Post post_id;

	public Report() {
		
	}

	public Report(int id_report, boolean enabled, User user_id, Post post_id) {
		super();
		this.id_report = id_report;
		this.enabled = enabled;
		this.user_id = user_id;
		this.post_id = post_id;
	}

	public int getId_report() {
		return id_report;
	}

	public void setId_report(int id_report) {
		this.id_report = id_report;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public Post getPost_id() {
		return post_id;
	}

	public void setPost_id(Post post_id) {
		this.post_id = post_id;
	}
	
}
