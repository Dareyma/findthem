package com.spring.core.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.core.entity.Post;
import com.spring.core.entity.Reply;

@Repository("replyRepository")
public interface ReplyRepository extends JpaRepository<Reply, Serializable>{
	
	List<Reply> findAllByPost(Post post);
	
}
