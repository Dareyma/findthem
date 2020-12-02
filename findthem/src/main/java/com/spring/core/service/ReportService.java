package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.Report;
import com.spring.core.model.ReportModel;

public interface ReportService {
	
	public abstract List<ReportModel> listAllReports();
	
	public abstract ReportModel addReport(ReportModel reportModel);
	
	public abstract ReportModel updateReport(ReportModel reportModel);
	
	public abstract int removeReport(int id);
	
	//transformar entidad a modelo
    public abstract Report transform(ReportModel reportModel);

    //transformar modelo a entidad
    public abstract ReportModel transform(Report report);
}
