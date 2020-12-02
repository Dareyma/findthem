package com.spring.core.model;

public class LikeModel {

	private int id_like;
	
	private boolean enabled;
		
	private UserModel user_id;
	
	private PostModel post_id;

	public LikeModel() {
		
	}

	public LikeModel(int id_like, boolean enabled, UserModel user_id, PostModel post_id) {
		super();
		this.id_like = id_like;
		this.enabled = enabled;
		this.user_id = user_id;
		this.post_id = post_id;
	}

	public int getId_like() {
		return id_like;
	}

	public void setId_like(int id_like) {
		this.id_like = id_like;
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
