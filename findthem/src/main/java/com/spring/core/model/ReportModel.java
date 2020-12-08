package com.spring.core.model;

public class ReportModel {
	
	private int id_report;
	
	private boolean enabled;
	
	private UserModel user_id;
	
	private PostModel post_id;

	public ReportModel() {
		
	}

	public ReportModel(int id_report, boolean enabled, UserModel user_id, PostModel post_id) {
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

	public UserModel getUser_id() {
		return user_id;
	}

	public void setUser_id(UserModel user_id) {
		this.user_id = user_id;
	}

	public PostModel getPost_id() {
		return post_id;
	}

	public void setPost_id(PostModel post_id) {
		this.post_id = post_id;
	}
	
	
}
