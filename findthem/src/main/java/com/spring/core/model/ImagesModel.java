package com.spring.core.model;

import com.spring.core.entity.Post;
import com.spring.core.entity.Reply;

public class ImagesModel {

	private int id;
	private String name;
	private Post post_id;
	private Reply reply_id;
	
	public ImagesModel() {
		
	}
	
	public ImagesModel(int id, String name, Post post_id, Reply reply_id) {
		super();
		this.id = id;
		this.name = name;
		this.post_id = post_id;
		this.reply_id = reply_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Post getPost_id() {
		return post_id;
	}
	public void setPost_id(Post post_id) {
		this.post_id = post_id;
	}
	public Reply getReply_id() {
		return reply_id;
	}
	public void setReply_id(Reply reply_id) {
		this.reply_id = reply_id;
	}
	
	
}
