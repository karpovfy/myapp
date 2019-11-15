package jman.service;

import jman.domain.DeptReport;

import java.util.List;

public interface DeptReportService {
    List<DeptReport> getReports();
    DeptReport getDeptReport(int id);
}
