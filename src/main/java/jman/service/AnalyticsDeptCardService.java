package jman.service;

import jman.domain.AnalyticsDeptCard;

import java.util.List;

public interface AnalyticsDeptCardService {
    List<AnalyticsDeptCard> getCard(int deptId);
}
