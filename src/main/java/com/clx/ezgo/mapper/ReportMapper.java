package com.clx.ezgo.mapper;

import com.clx.ezgo.entity.Report;

import java.util.List;

public interface ReportMapper {
    public List<Report> selectReportByUser(String userID);
    public void addReport(Report report);
}
