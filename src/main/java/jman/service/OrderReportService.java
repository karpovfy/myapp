package jman.service;

import jman.domain.OrderReport;

import java.util.List;

public interface OrderReportService {
    void addFile(OrderReport file);
    OrderReport getFile(int id);
    List<OrderReport> getOrderFiles(int id);
    List<OrderReport> getUserFiles(int id);
}
