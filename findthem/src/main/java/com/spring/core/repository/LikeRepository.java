package com.spring.core.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.core.entity.Like;
import com.spring.core.entity.Post;
import com.spring.core.entity.User;

@Repository("likeRepository")
public interface LikeRepository extends JpaRepository<Like, Serializable>{

	Optional<Like> findByUserAndPost(User user, Post post);

}
