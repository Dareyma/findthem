package com.spring.core.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.core.entity.Post;
import com.spring.core.entity.Report;
import com.spring.core.entity.User;

@Repository("reportRepository")
public interface ReportRepository extends JpaRepository<Report, Serializable>{

	Optional<Report> findByUserAndPost(User user, Post post);

}
