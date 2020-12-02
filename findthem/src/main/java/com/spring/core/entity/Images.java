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
@Table(name="images")
public class Images {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="post_id", nullable = true)
	private Post post_id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="reply_id", nullable = true)
	private Reply reply_id;
	
	public Images() {
		
	}

	public Images(int id, String name, Post post_id, Reply reply_id) {
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
