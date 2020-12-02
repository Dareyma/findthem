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
@Table(name="reply")
public class Reply {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="texto")
	private String texto;
	
	@Column(name="image", nullable = true)
	private String image;
	
	@Column(name="date")
	private String date;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user_id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="post_id")
	private Post post_id;

	public Reply() {
		
	}

	public Reply(int id, String texto, String image, String date, User user_id, Post post_id) {
		super();
		this.id = id;
		this.texto = texto;
		this.image = image;
		this.date = date;
		this.user_id = user_id;
		this.post_id = post_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setText(String texto) {
		this.texto = texto;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
