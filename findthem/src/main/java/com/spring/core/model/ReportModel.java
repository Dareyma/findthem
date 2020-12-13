package com.spring.core.model;

public class ReportModel {
	
	private int id_report;
	
	private boolean enabled;
	
	private UserModel user;
	
	private PostModel post;

	public ReportModel() {
		
	}

	public ReportModel(int id_report, boolean enabled, UserModel user, PostModel post) {
		super();
		this.id_report = id_report;
		this.enabled = enabled;
		this.user = user;
		this.post = post;
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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}	
	
}
