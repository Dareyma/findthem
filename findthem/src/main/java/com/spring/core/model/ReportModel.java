package com.spring.core.model;

import com.spring.core.entity.Post;
import com.spring.core.entity.User;

public class ReportModel {
	
	private int id_report;
	
	private boolean enabled;
	
	private User user_id;
	
	private Post post_id;

	public ReportModel() {
		
	}

	public ReportModel(int id_report, boolean enabled, User user_id, Post post_id) {
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
