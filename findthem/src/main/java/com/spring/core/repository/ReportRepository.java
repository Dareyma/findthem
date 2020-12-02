package com.spring.core.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.core.entity.Report;

@Repository("reportRepository")
public interface ReportRepository extends JpaRepository<Report, Serializable>{

}
