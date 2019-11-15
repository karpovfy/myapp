package jman.service;

import jman.domain.ReportFile;

import java.util.List;

public interface ReportFileService {
    void saveReport(ReportFile reportFile);
    void deleteReport(ReportFile reportFile);
    List<ReportFile> getDeptItemReports(int orderDeptId);
    ReportFile getReportFile(int id);
}