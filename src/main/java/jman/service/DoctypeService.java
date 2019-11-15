package jman.service;

import jman.domain.OrderType;

import java.util.List;

public interface DoctypeService {
    List<OrderType> getOrderTypes();
    List<OrderType> getAllOrderTypes();
    OrderType getOrderType(int id);
    void saveOrderType(OrderType orderType);
}
