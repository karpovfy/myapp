package jman.service;

import jman.domain.ViewExDepts;

import java.util.List;

public interface ViewExDeptsService {
    ViewExDepts getViewExDept(int id);
    List<ViewExDepts> getList(String where, int frow, int rows);
    Long getOrderCount(String query);
    List<ViewExDepts> getItemDepts(int id);
}
