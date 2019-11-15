package jman.service;


import jman.domain.AnalyticsDepts;

import java.util.List;

public interface AnalyticsDeptsService {
    List<AnalyticsDepts> getList();
    List<Object> getDeptCards(String startDate, String endDate, Short deptType);
}
