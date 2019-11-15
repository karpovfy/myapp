package jman.service;

import jman.domain.ViewOrder;

import java.util.List;

public interface OrderViewService {
    ViewOrder getViewOrder(int id);
    List<ViewOrder> getOrders(String query, int rfirst, int rcnt);
    Long getOrderCount(String query);
}
