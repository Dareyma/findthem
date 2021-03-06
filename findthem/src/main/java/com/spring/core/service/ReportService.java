package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.Post;
import com.spring.core.entity.Report;
import com.spring.core.entity.User;
import com.spring.core.model.PostModel;
import com.spring.core.model.ReportModel;
import com.spring.core.model.UserModel;

public interface ReportService {
	
	public abstract List<ReportModel> listAllReports();
	
	public abstract ReportModel addReport(ReportModel reportModel);
	
	public abstract ReportModel updateReport(ReportModel reportModel);
	
	public abstract int removeReport(int id);
	
	ReportModel findById(int id);
	
	//transformar entidad a modelo
    public abstract Report transform(ReportModel reportModel);

    //transformar modelo a entidad
    public abstract ReportModel transform(Report report);

	Post transform(PostModel postModel);

	PostModel transform(Post post);

	User transform(UserModel userModel);

	UserModel transform(User user);

	ReportModel findByUserAndPost(PostModel postModel, UserModel userModel);

	
}
