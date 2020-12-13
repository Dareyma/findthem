package com.spring.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.core.entity.Post;
import com.spring.core.entity.Report;
import com.spring.core.entity.User;
import com.spring.core.model.PostModel;
import com.spring.core.model.ReportModel;
import com.spring.core.model.UserModel;
import com.spring.core.repository.ReportRepository;
import com.spring.core.service.ReportService;

@Service("reportServiceImpl")
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	@Qualifier("reportRepository")
	private ReportRepository reportRepository;
	
	@Autowired
    private DozerBeanMapper dozer;
	
	@Override
    public List<ReportModel> listAllReports() {
        List<Report> listReport=reportRepository.findAll();
        List<ReportModel> listaCM=new ArrayList<>();
        listReport.forEach(a->{
        	ReportModel reportModel=transform(a);
            listaCM.add(reportModel);
        });

        // Ordenar por nombre
        // Collections.sort(listaCM, (ReplyModel r1, ReplyModel r2) -> c1.getNombre().compareTo(c2.getNombre()));

        return listaCM;
    }

	@Override
    public ReportModel addReport(ReportModel reportModel) {
		Report report=transform(reportModel);
        return transform(reportRepository.save(report));
    }
	
	@Override
    public ReportModel updateReport(ReportModel reportModel) {
		Report report=transform(reportModel);
        return transform(reportRepository.save(report));
    } 
	
	@Override
    public int removeReport(int id) {
		reportRepository.deleteById(id);
        return 0;
    }
	
	@Override
    public ReportModel findById(int id) {
		Optional<Report> reportResponse = reportRepository.findById(id);
		return transform(reportResponse.get());
    }
	
	@Override
    public ReportModel findByUserAndPost(PostModel postModel, UserModel userModel) {
		User user = transform(userModel);
		Post post = transform(postModel);
		Optional<Report> reportResponse = reportRepository.findByUserAndPost(user, post);
		
		if(reportResponse.isPresent()) {
			return transform(reportResponse.get());
		} else {
			return null;
		}
    }
	
	@Override
    public Report transform(ReportModel reportModel) {
        return dozer.map(reportModel, Report.class);
    }
	
	@Override
    public ReportModel transform(Report report) {
        return dozer.map(report, ReportModel.class);
    }
	
	@Override
    public Post transform(PostModel postModel) {
        return dozer.map(postModel, Post.class);
    }
	
	@Override
    public PostModel transform(Post post) {
        return dozer.map(post, PostModel.class);
    }
	
	@Override
    public User transform(UserModel userModel) {
        return dozer.map(userModel, User.class);
    }
	
	@Override
    public UserModel transform(User user) {
        return dozer.map(user, UserModel.class);
    }
}
